package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Loan;
import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.model.LoanSearchByUser;
import org.springframework.data.domain.Page;

public interface LoanRepositoryPort {
    Loan save(Loan loan);
    Integer countLoansActive(Long userId);
    Page<LoanInformation> findLoansByUser(LoanSearchByUser loanSearchByUser);
}
