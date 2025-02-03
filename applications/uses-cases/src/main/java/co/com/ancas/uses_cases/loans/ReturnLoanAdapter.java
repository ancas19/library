package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.uses_cases.books.ChangeCopiesAvailablesPerBookAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
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
    private final LoanRepositoryPort loanRepositoryPort;
    private final VerifyUserIdDifferentAdapter verifyUserIdDifferentAdapter;
    private final ChangeCopiesAvailablesPerBookAdapter changeCopiesAvailablesPerBookAdapter;
    private final FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;

    @Override
    public LoanReturnResult execute(List<LoanReturn> loanReturns) throws MessagingException, IOException {
        List<Loan> loansFound = loanRepositoryPort.findLoansByIds(loanReturns.stream().map(LoanReturn::getIdLoan).toList());
        if(loansFound.isEmpty() || loansFound.size()!=loanReturns.size()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOANS_FOUND.getMessage());
        }
        verifyUserIdDifferentAdapter.execute(loansFound);
        Long iduser = loansFound.getFirst().getUserId();
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
            changeCopiesAvailablesPerBookAdapter.execute(AvailableCopiesUpdate.builder()
                    .bookId(loan.getBookId())
                    .copies(1)
                    .action(Constants.INCREASE)
                    .build());
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
        if(loanReturn.getFine()>(fine)){
            throw new BadRequestException(Messages.MESSAGE_ERROR_FINE_GREATER_THAN_REAL.getMessage().formatted(loanReturn.getFine(),fine));

        }
        loan.setFine(fine);
        loan.setPaid(loanReturn.getFine().equals(fine) ? YES.getConstant() : NO.getConstant());
        loan.setComments(loanReturn.getComment());
    }

    private Integer calculateDelayedDays(Loan loan,UserMembershipInfo userMembershipInfoFound) {
        if(!loan.getReturnDate().isAfter(loan.getDueDate())){
            return 0;
        }
        int delayedDays= (int) (loan.getReturnDate().toEpochDay()-loan.getDueDate().toEpochDay());
        return  delayedDays>userMembershipInfoFound.getGracePeriodDays()?(delayedDays- userMembershipInfoFound.getGracePeriodDays()):0;
    }

    private LoanReturn findLoanReturn(List<LoanReturn> loans, Loan loan) {
        Optional<LoanReturn> loanFound= loans.stream().filter(loanReturn -> loanReturn.getIdLoan().equals(loan.getId())).findFirst();
        if (loanFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ERROR_LOAN_NOT_FOUND.getMessage());
        }
        return loanFound.get();
    }
}
