package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.BookCreation;
import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.FileData;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import co.com.ancas.uses_cases.util.DownloadImageAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static co.com.ancas.models.enums.Constants.*;
import static co.com.ancas.models.enums.Constants.SUBJECT_FILES_PROCESSED;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadBooksByFileAdapter implements IUseCaseVoid<FileData> {
    private final CreateBookAdapter createBookAdapter;
    private final DownloadImageAdapter downloadImageAdapter;
    private final EmailRepositoryPort emailRepositoryPort;
    private final FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    private final UserRepositoryPort userRepositoryPort;


    @Override
    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void execute(FileData fileData) throws MessagingException, IOException {
        List<String> emails =this.userRepositoryPort.findEmailsAdmins();
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(fileData.getBase64());
            String dataAuthor = new String(decodedBytes);
            String[] books = dataAuthor.split("\n");
            for (int i = 1; i < books.length; i++) {
                String[] bookData = books[i].split("\\|");
                if (bookData.length != 9) {
                    log.error("Data hasn't the correct length {}", Arrays.toString(bookData));
                    throw new BadRequestException(Messages.MESSAGE_ERROR_AUTHOR_DATA_INVALID.getMessage());
                }
                BookCreation bookCreation = processBookData(bookData);
                createBookAdapter.execute(bookCreation);
            }
            String emailTemplate = findEmailTemplateBySubjectAdapter.execute(FILE_CORRECT.getConstant());
            emailRepositoryPort.sendEmail(
                    Email.builder()
                            .recipient(emails)
                            .subject(SUBJECT_FILES_PROCESSED_BOOKS.getConstant())
                            .body(emailTemplate)
                            .build()
            );
            log.info("File with authors processed");
        }catch (Exception e){
            log.error("Error processing file {}", e);
            String emailTemplate = findEmailTemplateBySubjectAdapter.execute(ERROR_FILE.getConstant());
            emailRepositoryPort.sendEmail(
                    Email.builder()
                            .recipient(emails)
                            .subject(SUBJECT_ERROR_FILE_BOOKS.getConstant())
                            .body(emailTemplate)
                            .build()
            );
            throw new BadRequestException(Messages.MESSAGE_ERROR_FILE_PROCESSING.getMessage());
        }
    }

    private BookCreation processBookData(String[] bookData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthdate = LocalDate.parse(bookData[3], formatter);
        String imageBase64 = downloadImageAdapter.downloadImage(bookData[8]);
        return BookCreation.builder()
                .title(bookData[0].toUpperCase())
                .isbn(bookData[1])
                .author(bookData[2].toUpperCase())
                .publishDate(birthdate)
                .genre(bookData[4].toUpperCase())
                .availableCopies(Integer.parseInt(bookData[5]))
                .blurb(bookData[6])
                .available(bookData[7])
                .nameFile("%s-%s".formatted(bookData[0],LocalDate.now()))
                .base64(imageBase64)
                .build();
    }
}
