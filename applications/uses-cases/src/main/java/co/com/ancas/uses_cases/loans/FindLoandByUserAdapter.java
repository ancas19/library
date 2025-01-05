package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.model.LoanSearchByUser;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FindLoandByUserAdapter  implements IUseCase<LoanSearchByUser, Page<LoanInformation>> {
    private final LoanRepositoryPort loanRepositoryPort;

    @Override
    public Page<LoanInformation> execute(LoanSearchByUser loanSearchByUser) throws MessagingException, IOException {
        Page<LoanInformation> loanInformation = loanRepositoryPort.findLoansByUser(loanSearchByUser);
        if(loanInformation.getContent().isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ERROR_LOAN_NOT_FOUND.getMessage());
        }
        return loanInformation;
    }
}
