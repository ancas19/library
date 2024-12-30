package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindBookInformationByIdAdapter implements IUseCase<Long, BookInformation> {
    private final BookRepositoryPort bookRepositoryPort;


    @Override
    public BookInformation execute(Long id) {
        Optional<BookInformation> bookFound=bookRepositoryPort.findBookInformationById(id);
        if (bookFound.isEmpty()) {
            throw new NotFoundException(Messages.MESSAGE_BOOK_NOT_FOUND.getMessage());
        }
        return bookFound.get();
    }
}
