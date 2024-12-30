package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Loan;

public interface LoanRepositoryPort {
    Loan save(Loan loan);
    Integer countLoansActive(Long userId);
}
