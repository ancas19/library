package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Loan;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.LoanEntity;
import co.com.ancas.postgres.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanRepositoryAdapter implements LoanRepositoryPort {
    private final LoanRepository loanRepository;

    @Override
    public Loan save(Loan loan) {
        return Mapper.map(this.loanRepository.save(Mapper.map(loan, LoanEntity.class)), Loan.class);
    }

    @Override
    public Integer countLoansActive(Long userId) {
        return this.loanRepository.countLoansActive(userId);
    }
}
