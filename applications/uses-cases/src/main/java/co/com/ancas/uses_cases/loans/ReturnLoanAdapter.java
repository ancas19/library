package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.ForbiddenException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import co.com.ancas.uses_cases.user.CurrentUserAdapter;
import co.com.ancas.uses_cases.user.FindUserAndMembershipInfoByUserIdAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ReturnLoanAdapter implements IUseCase<List<LoanReturn>, LoanReturnResult> {
    private final CurrentUserAdapter currentUserAdapter;
    private final LoanRepositoryPort loanRepositoryPort;
    private final FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;

    @Override
    public LoanReturnResult execute(List<LoanReturn> loans) throws MessagingException, IOException {
        List<Loan> loansFound = loanRepositoryPort.findLoansByIds(loans.stream().map(LoanReturn::getIdLoan).toList());
        if(loansFound.size()!=loans.size() || loansFound.isEmpty()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOANS_FOUND.getMessage());
        }
        verifyUserIdDifferent(loansFound);
        Long iduser = loansFound.get(0).getUserId();
        verifyCurrentUserId(iduser);
        UserMembershipInfo userMembershipInfoFound=findUserAndMembershipInfoByUserIdAdapter.execute(iduser);
        Integer totalDelayedDays=0;
        Double totalFine=0.0;
        for(Loan loan:loansFound){
            loan.setReturnDate(LocalDate.now());
            Integer delayedDays = calculateDelayedDays(loan, userMembershipInfoFound);
            loan.setDaysDelayed(delayedDays);
            Double fine = userMembershipInfoFound.getDailyFine()*delayedDays;
            loan.setFine(fine);
            loan.setPaid(Constants.YES.getConstant());
            loan.setComments(findLoanComments(loans,loan));
            loanRepositoryPort.save(loan);
            totalDelayedDays+=delayedDays;
            totalFine+=fine;
        }
        return LoanReturnResult.builder()
                .delayedDays(totalDelayedDays)
                .total(totalFine)
                .build();
    }

    private String findLoanComments(List<LoanReturn> loans, Loan loan) {
        return loans.stream().filter(loanReturn -> loanReturn.getIdLoan().equals(loan.getId())).findFirst().get().getComment();
    }

    private Integer calculateDelayedDays(Loan loan,UserMembershipInfo userMembershipInfoFound) {
        if(!loan.getReturnDate().isAfter(loan.getDueDate())){
            return 0;
        }
        Integer delayedDays= (int) (loan.getReturnDate().toEpochDay()-loan.getDueDate().toEpochDay());
        return delayedDays>userMembershipInfoFound.getGracePeriodDays()?delayedDays:0;
    }

    private void verifyUserIdDifferent(List<Loan> loansFound) {
        Set<Long> userIds = new HashSet<>();
        for (Loan loan : loansFound) {
            userIds.add(loan.getUserId());
        }
        if (userIds.size() > 1) {
            throw new BadRequestException(Messages.MESSAGE_LOAN_RETURN_MULTIPLE_USERS.getMessage());
        }
    }
    public void verifyCurrentUserId(Long userId){
        CurrentUserInformation currentUserInformationFound=currentUserAdapter.execute();
        if(currentUserInformationFound.getRole().equalsIgnoreCase(Constants.USER.getConstant()) && !currentUserInformationFound.getUserId().equals(userId)){
            throw new ForbiddenException(Messages.MESSAGE_GENERAL_FORBIDDEN.getMessage());
        }
    }
}
