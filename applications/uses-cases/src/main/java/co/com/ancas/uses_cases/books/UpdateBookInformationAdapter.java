package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.authors.FindAuthorByFullNameAdapter;
import co.com.ancas.uses_cases.genres.FindGenreByValueAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class UpdateBookInformationAdapter  implements IUseCase<BookUpdate, BookInformation> {
    private final FindBookByIdAdapter findBookByIdAdapter;
    private final BookRepositoryPort bookRepositoryPort;
    private final FindAuthorByFullNameAdapter findAuthorByFullNameAdapter;
    private final FindGenreByValueAdapter findGenreByValueAdapter;
    private final FindBookInformationByIdAdapter findBookInformationByIdAdapter;

    @Override
    public BookInformation execute(BookUpdate bookUpdate) throws MessagingException, IOException {
        Book bookFound=findBookByIdAdapter.execute(bookUpdate.getId());
        boolean exists=this.bookRepositoryPort.existsByIsbnAndIdNot(bookUpdate.getIsbn(),bookFound.getId());
        if(exists){
            throw new BadRequestException(Messages.MESSAGE_ERROR_ISBN_ALREADY_EXISTS.getMessage());
        }
        exists=this.bookRepositoryPort.existsByTitleAndIdNot(bookUpdate.getTitle(),bookFound.getId());
        if(exists){
            throw new BadRequestException(Messages.MESSAGE_ERROR_TITLE_ALREADY_EXISTS.getMessage());
        }
        Genre genreFound = this.findGenreByValueAdapter.execute(bookUpdate.getGenre());
        Author authorFound = this.findAuthorByFullNameAdapter.execute(bookUpdate.getAuthor());
        bookFound.setTitle(bookUpdate.getTitle());
        bookFound.setIsbn(bookUpdate.getIsbn());
        bookFound.setAuthorId(authorFound.getId());
        bookFound.setPublishDate(bookUpdate.getPublishDate());
        bookFound.setGenreId(genreFound.getId());
        bookFound.setAvailableCopies(bookUpdate.getAvailableCopies());
        bookFound.setBlurb(bookUpdate.getBlurb());
        this.bookRepositoryPort.save(bookFound);
        return this.findBookInformationByIdAdapter.execute(bookFound.getId());
    }
}