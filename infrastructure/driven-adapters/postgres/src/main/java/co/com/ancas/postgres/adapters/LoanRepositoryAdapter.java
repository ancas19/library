package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Loan;
import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.model.LoanSearchByUser;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.LoanEntity;
import co.com.ancas.postgres.repositories.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        return this.loanRepository.countLoansActive(userId, LocalDate.now());
    }

    @Override
    public Page<LoanInformation> findLoansByUser(LoanSearchByUser loanSearchByUser) {
        Pageable pageable = PageRequest.of(loanSearchByUser.getPage(), loanSearchByUser.getSize());
        return this.loanRepository.findLoansByUser(loanSearchByUser.getDni(),loanSearchByUser.getSearchBook(),loanSearchByUser.getStartDate(),loanSearchByUser.getFinishDate(),pageable);
    }

    @Override
    public Page<LoanInformation> findReturnedLoansByUser(LoanSearchByUser loanSearchByUser) {
        Pageable pageable = PageRequest.of(loanSearchByUser.getPage(), loanSearchByUser.getSize());
        return this.loanRepository.findReturnedLoansByUser(loanSearchByUser.getDni(),loanSearchByUser.getSearchBook(),loanSearchByUser.getStartDate(),loanSearchByUser.getFinishDate(),pageable);
    }

    @Override
    public Page<LoanInformation> findExpiredLoansByUser(LoanSearchByUser loanSearchByUser) {
        Pageable pageable = PageRequest.of(loanSearchByUser.getPage(), loanSearchByUser.getSize());
        return this.loanRepository.findExpiredLoansByUser(loanSearchByUser.getDni(),loanSearchByUser.getSearchBook(),loanSearchByUser.getStartDate(),loanSearchByUser.getFinishDate(),LocalDate.now(),pageable);
    }

    @Override
    public Page<LoanInformation> findActiveLoansByUser(LoanSearchByUser loanSearchByUser) {
        Pageable pageable = PageRequest.of(loanSearchByUser.getPage(), loanSearchByUser.getSize());
        return this.loanRepository.findActiveLoansByUser(loanSearchByUser.getDni(),loanSearchByUser.getSearchBook(),loanSearchByUser.getStartDate(),loanSearchByUser.getFinishDate(),pageable);
    }

    @Override
    public boolean existsByBookIdAndReturnDateIsNull(Long id, Long userId) {
        return this.loanRepository.existsByBookIdAndUserIdAndReturnDateIsNull(id, userId);
    }

    @Override
    public List<Loan> findLoansByIds(List<Long> longs) {
        return this.loanRepository.findAllById(longs).stream().map(loanEntity -> Mapper.map(loanEntity, Loan.class)).toList();
    }

    @Override
    public boolean existsLoansWithoutPaid(Long userId) {
        Integer loansWithoutPaid= this.loanRepository.existsLoansWithoutPaid(userId);
        return loansWithoutPaid>0;
    }

    @Override
    public boolean existsExpiredLoans(Long userId) {
        Integer expiredLoans= this.loanRepository.existsExpiredLoans(userId, LocalDate.now());
        return expiredLoans>0;
    }
}
