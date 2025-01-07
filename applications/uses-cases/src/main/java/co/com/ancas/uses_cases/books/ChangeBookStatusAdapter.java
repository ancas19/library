package co.com.ancas.uses_cases.books;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static co.com.ancas.models.enums.Constants.NO;
import static co.com.ancas.models.enums.Constants.YES;

@Component
@RequiredArgsConstructor
public class ChangeBookStatusAdapter implements IUseCaseVoid<Long> {
    private final BookRepositoryPort bookRepositoryPort;
    private final FindBookByIdAdapter findBookByIdAdapter;

    public void execute(Long bookId) throws MessagingException, IOException {
        Book bookFound = findBookByIdAdapter.execute(bookId);
        String avaliable = bookFound.getAvailable();
        bookFound.setAvailable(avaliable.equals(YES.getConstant()) ? NO.getConstant() : YES.getConstant());
        bookRepositoryPort.save(bookFound);
    }
}
