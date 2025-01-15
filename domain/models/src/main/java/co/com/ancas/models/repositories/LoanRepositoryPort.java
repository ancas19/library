package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Loan;
import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.model.LoanSearchByUser;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LoanRepositoryPort {
    Loan save(Loan loan);
    Integer countLoansActive(Long userId);
    Page<LoanInformation> findLoansByUser(LoanSearchByUser loanSearchByUser);
    Page<LoanInformation> findReturnedLoansByUser(LoanSearchByUser loanSearchByUser);
    Page<LoanInformation> findExpiredLoansByUser(LoanSearchByUser loanSearchByUser);
    Page<LoanInformation> findActiveLoansByUser(LoanSearchByUser loanSearchByUser);
    boolean existsByBookIdAndReturnDateIsNull(Long id, Long userId);
    List<Loan> findLoansByIds(List<Long> longs);
    boolean existsLoansWithoutPaid(Long userId);
    boolean existsExpiredLoans(Long userId);
    List<Loan> findLoanByDni(String dniUser);
    List<Loan> findLoanNoPaidByDni(String dniUser);
}
