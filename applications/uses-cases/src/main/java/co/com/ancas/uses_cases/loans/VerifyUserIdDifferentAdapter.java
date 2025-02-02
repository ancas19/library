package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.Loan;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
public class VerifyUserIdDifferentAdapter implements IUseCaseVoid<List<Loan>> {
    @Override
    public void execute(List<Loan> loansFound) throws MessagingException, IOException {
        Set<Long> userIds = new HashSet<>();
        for (Loan loan : loansFound) {
            if(Objects.nonNull(loan.getReturnDate())){
                log.error("Loan already returned {}", loan);
                throw new BadRequestException(Messages.MESSAGE_ERROR_LOAN_ALREADY_RETURNED.getMessage());
            }
            userIds.add(loan.getUserId());
        }
        if (userIds.size() > 1) {
            log.error("Multiple users in the same loan {}", userIds);
            throw new BadRequestException(Messages.MESSAGE_LOAN_RETURN_MULTIPLE_USERS.getMessage());
        }
    }
}
