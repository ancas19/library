package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.AvailableCopiesUpdate;
import co.com.ancas.models.model.Book;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChangeCopiesAvailablesPerBookAdapter  implements IUseCaseVoid<AvailableCopiesUpdate> {
   private final BookRepositoryPort bookRepositoryPort;
    private final FindBookByIdAdapter findBookByIdAdapter;

    @Override
    public void execute(AvailableCopiesUpdate availableCopiesUpdate) throws MessagingException, IOException {
        Book bookFound = findBookByIdAdapter.execute(availableCopiesUpdate.getBookId());
        if(availableCopiesUpdate.getAction().equals(Constants.DISCOUNT)){
            bookFound.setAvailableCopies(bookFound.getAvailableCopies()-availableCopiesUpdate.getCopies());
            verifyNumberMoreThanZero(bookFound);
            this.bookRepositoryPort.save(bookFound);
            return;
        }
        bookFound.setAvailableCopies(bookFound.getAvailableCopies()+availableCopiesUpdate.getCopies());
        bookRepositoryPort.save(bookFound);
    }

    private void verifyNumberMoreThanZero(Book bookFound) {
        if(bookFound.getAvailableCopies()<0){
            log.error("Available copies can't be less than 0, {}",bookFound );
            throw new BadRequestException(Messages.MESSAGE_ERROR_AVAILABLE_COPIES.getMessage());
        }
    }
}
