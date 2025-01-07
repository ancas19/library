package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.BookSearchCriteria;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FindBooksByCriteriaAdapter implements IUseCase<BookSearchCriteria, Page<BookInformation>> {
    private final BookRepositoryPort bookRepositoryPort;

    @Override
    @Cacheable(value = "books", key = "#bookSearchCriteria.toString()")
    public Page<BookInformation> execute(BookSearchCriteria bookSearchCriteria) throws MessagingException, IOException {
        Page<BookInformation> booksFound = bookRepositoryPort.findBooksByCriteria(bookSearchCriteria);
        if (booksFound.getContent().isEmpty()) {
            throw new NotFoundException(Messages.MESSAGE_BOOK_NOT_FOUND.getMessage());
        }
        return booksFound;
    }
}
