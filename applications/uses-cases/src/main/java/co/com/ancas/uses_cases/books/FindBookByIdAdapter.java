package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
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
public class FindBookByIdAdapter implements IUseCase<Long, Book>{

    private final BookRepositoryPort bookRepositoryPort;
    @Override
    public Book execute(Long bookId) throws MessagingException, IOException {
        Optional<Book> bookFound=bookRepositoryPort.findById(bookId);
        if (bookFound.isEmpty()) {
            throw new NotFoundException(Messages.MESSAGE_BOOK_NOT_FOUND.getMessage());
        }
        return bookFound.get();
    }
}
