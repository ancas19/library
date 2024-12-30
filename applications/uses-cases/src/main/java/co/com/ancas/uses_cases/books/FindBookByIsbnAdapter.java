package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.Book;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindBookByIsbnAdapter  implements IUseCase<String, Book> {
    private final BookRepositoryPort bookRepositoryPort;
    @Override
    public Book execute(String isbn) throws MessagingException, IOException {
        Optional<Book> bookFound = bookRepositoryPort.findBookByIsbn(isbn);
        if (bookFound.isEmpty()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_BOOK_NOT_FOUND_BY_ISBN.getMessage().formatted(isbn));
        }
        return bookFound.get();
    }
}
