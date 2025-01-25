package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.AuthorCreation;
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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static co.com.ancas.models.enums.Constants.ERROR_FILE;
import static co.com.ancas.models.enums.Constants.FILE_CORRECT;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadAuthorByFileAdapter  implements IUseCaseVoid<FileData> {
    private final CreateAuthorAdapter createAuthorAdapter;
    private final DownloadImageAdapter downloadImageAdapter;
    private final EmailRepositoryPort emailRepositoryPort;
    private final FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    private final UserRepositoryPort userRepositoryPort;


    @Override
    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void execute(FileData fileData) throws MessagingException, IOException {
        List<String> emails =this.userRepositoryPort.findEmailsAdmins();
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(fileData.getBase64());
            String dataAuthor = new String(decodedBytes);
            String[] authors = dataAuthor.split("\n");
            for(int i=1; i<authors.length; i++) {
                String[] authorData = authors[i].split("\\|");
                if(authorData.length != 5) {
                    log.error("Data hasn't the correct length {}", Arrays.toString(authorData));
                    throw new BadRequestException(Messages.MESSAGE_ERROR_AUTHOR_DATA_INVALID.getMessage());
                }
                AuthorCreation authorCreation = processAuthorData(authorData);
                createAuthorAdapter.execute(authorCreation);
            }
            String emailTemplate = findEmailTemplateBySubjectAdapter.execute(FILE_CORRECT.getConstant());
            emailRepositoryPort.sendEmail(
                    Email.builder()
                            .recipient(emails)
                            .subject("File with authors processed")
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
                            .subject("Error processing file with authors")
                            .body(emailTemplate)
                            .build()
            );
            throw new BadRequestException(Messages.MESSAGE_ERROR_FILE_PROCESSING.getMessage());
        }

    }

    private AuthorCreation processAuthorData(String[] authorData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthdate = LocalDate.parse(authorData[2], formatter);
        String imageBase64 = downloadImageAdapter.downloadImage(authorData[4]);
        return AuthorCreation.builder()
                .fullName(authorData[0].toUpperCase())
                .nationality(authorData[1].toUpperCase())
                .birthdate(birthdate)
                .bio(authorData[3])
                .nameFile("%s-%s".formatted(authorData[0], authorData[2]))
                .base64(imageBase64)
                .build();
    }
}
