package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.authors.FindAuthorByFullNameAdapter;
import co.com.ancas.uses_cases.genres.FindGenreByValueAdapter;
import co.com.ancas.uses_cases.images.UploadImageAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CreateBookAdapter  implements IUseCase<BookCreation, BookInformation> {
    private final BookRepositoryPort bookRepositoryPort;
    private final FindAuthorByFullNameAdapter findAuthorByFullNameAdapter;
    private final UploadImageAdapter uploadImageAdapter;
    private final FindGenreByValueAdapter findGenreByValueAdapter;

    @Override
    public BookInformation execute(BookCreation bookCreation) throws MessagingException, IOException {
        boolean exists=this.bookRepositoryPort.existsByIsbn(bookCreation.getIsbn());
        if(exists){
            throw new BadRequestException(Messages.MESSAGE_ERROR_ISBN_ALREADY_EXISTS.getMessage());
        }
        exists=this.bookRepositoryPort.existsByTitle(bookCreation.getTitle());
        if(exists){
            throw new BadRequestException(Messages.MESSAGE_ERROR_TITLE_ALREADY_EXISTS.getMessage());
        }
        Genre genreFound = this.findGenreByValueAdapter.execute(bookCreation.getGenre());
        Author authorFound = this.findAuthorByFullNameAdapter.execute(bookCreation.getAuthor());
        Image imageUploaded = this.uploadImageAdapter.execute(
                ImageUpload.builder()
                        .nameFile(bookCreation.getNameFile())
                        .base64(bookCreation.getBase64())
                        .build()
        );
        Book bookCreated = this.bookRepositoryPort.save(
                Book.builder()
                        .title(bookCreation.getTitle())
                        .isbn(bookCreation.getIsbn())
                        .authorId(authorFound.getId())
                        .publishDate(bookCreation.getPublishDate())
                        .genreId(genreFound.getId())
                        .availableCopies(bookCreation.getAvailableCopies())
                        .blurb(bookCreation.getBlurb())
                        .imageId(imageUploaded.getId())
                        .build()
        );
        return BookInformation.builder()
                .id(bookCreated.getId())
                .title(bookCreated.getTitle())
                .isbn(bookCreated.getIsbn())
                .author(authorFound.getFullName())
                .publishDate(bookCreated.getPublishDate())
                .genre(genreFound.getValue())
                .availableCopies(bookCreated.getAvailableCopies())
                .blurb(bookCreation.getBlurb())
                .booImage(imageUploaded.getFilePath())
                .build();
    }
}
