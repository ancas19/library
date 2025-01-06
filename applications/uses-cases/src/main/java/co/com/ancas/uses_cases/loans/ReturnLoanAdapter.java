package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.ForbiddenException;
import co.com.ancas.models.exceptions.NotFoundException;
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
import java.util.*;

import static co.com.ancas.models.enums.Constants.NO;
import static co.com.ancas.models.enums.Constants.YES;

@Component
@RequiredArgsConstructor
public class ReturnLoanAdapter implements IUseCase<List<LoanReturn>, LoanReturnResult> {
    private final CurrentUserAdapter currentUserAdapter;
    private final LoanRepositoryPort loanRepositoryPort;
    private final FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;

    @Override
    public LoanReturnResult execute(List<LoanReturn> loanReturns) throws MessagingException, IOException {
        List<Loan> loansFound = loanRepositoryPort.findLoansByIds(loanReturns.stream().map(LoanReturn::getIdLoan).toList());
        if(loansFound.isEmpty() || loansFound.size()!=loanReturns.size()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOANS_FOUND.getMessage());
        }
        verifyUserIdDifferent(loansFound);
        Long iduser = loansFound.getFirst().getUserId();
        verifyCurrentUserId(iduser);
        UserMembershipInfo membershipInfo=findUserAndMembershipInfoByUserIdAdapter.execute(iduser);
        int totalDelayedDays=0;
        double totalFine=0.0;
        for(Loan loan:loansFound){
            LoanReturn matchedLoanReturn = findLoanReturn(loanReturns, loan);
            updateLoanDetails(loan, matchedLoanReturn, membershipInfo);
            boolean paid=loan.getPaid().equals(YES.getConstant());
            totalDelayedDays += paid?loan.getDaysDelayed():0;
            totalFine += paid?loan.getFine():0.0;
            loanRepositoryPort.save(loan);
        }
        return LoanReturnResult.builder()
                .delayedDays(totalDelayedDays)
                .total(totalFine)
                .build();
    }


    private void updateLoanDetails(Loan loan, LoanReturn loanReturn, UserMembershipInfo membershipInfo) {
        loan.setReturnDate(LocalDate.now());
        int delayedDays = calculateDelayedDays(loan, membershipInfo);
        loan.setDaysDelayed(delayedDays);

        double fine = membershipInfo.getDailyFine() * delayedDays;
        loan.setFine(fine);

        loan.setPaid(loanReturn.getFine().equals(fine) ? YES.getConstant() : NO.getConstant());
        loan.setComments(loanReturn.getComment());
    }

    private Integer calculateDelayedDays(Loan loan,UserMembershipInfo userMembershipInfoFound) {
        if(!loan.getReturnDate().isAfter(loan.getDueDate())){
            return 0;
        }
        int delayedDays= (int) (loan.getReturnDate().toEpochDay()-loan.getDueDate().toEpochDay());
        return delayedDays>userMembershipInfoFound.getGracePeriodDays()?delayedDays:0;
    }

    private void verifyUserIdDifferent(List<Loan> loansFound) {
        Set<Long> userIds = new HashSet<>();
        for (Loan loan : loansFound) {
            if(Objects.nonNull(loan.getReturnDate())){
                throw new BadRequestException(Messages.MESSAGE_ERROR_LOAN_ALREADY_RETURNED.getMessage());
            }
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

    private LoanReturn findLoanReturn(List<LoanReturn> loans, Loan loan) {
        Optional<LoanReturn> loanFound= loans.stream().filter(loanReturn -> loanReturn.getIdLoan().equals(loan.getId())).findFirst();
        if (loanFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ERROR_LOAN_NOT_FOUND.getMessage());
        }
        return loanFound.get();
    }
}
