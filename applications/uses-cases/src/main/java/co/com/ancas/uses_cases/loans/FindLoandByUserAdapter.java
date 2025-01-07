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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import static co.com.ancas.models.enums.TypeSearch.*;

@Component
@RequiredArgsConstructor
public class FindLoandByUserAdapter  implements IUseCase<LoanSearchByUser, Page<LoanInformation>> {
    private final LoanRepositoryPort loanRepositoryPort;
    private Map<String, Function<LoanSearchByUser, Page<LoanInformation>>> typeSearch;

    @Override
    public Page<LoanInformation> execute(LoanSearchByUser loanSearchByUser) throws MessagingException, IOException {
        initTypeSearch();
        Function<LoanSearchByUser, Page<LoanInformation>> searchFunction = typeSearch.get(loanSearchByUser.getTypeSearch().getTypeSearch());
        if(Objects.isNull(searchFunction)){
            throw new NotFoundException(Messages.MESSAGE_ERROR_TYPE_SEARCH_NOT_FOUND.getMessage());
        }
        Page<LoanInformation> loanInformation = searchFunction.apply(loanSearchByUser);
        if(loanInformation.getContent().isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ERROR_LOAN_NOT_FOUND.getMessage());
        }
        return loanInformation;
    }

    private void initTypeSearch() {
        typeSearch=new HashMap<>();
        typeSearch.put(HISTORICAL.getTypeSearch(), loanRepositoryPort::findActiveLoansByUser);
        typeSearch.put(RETURNED.getTypeSearch(), loanRepositoryPort::findReturnedLoansByUser);
        typeSearch.put(EXPIRED.getTypeSearch(), loanRepositoryPort::findExpiredLoansByUser);
        typeSearch.put(ACTIVE.getTypeSearch(), loanRepositoryPort::findActiveLoansByUser);
    }
}
