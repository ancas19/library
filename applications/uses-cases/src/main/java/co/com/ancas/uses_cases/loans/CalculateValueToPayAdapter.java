package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.uses_cases.books.FindBookByIdAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import co.com.ancas.uses_cases.user.FindUserAndMembershipInfoByUserIdAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class CalculateValueToPayAdapter  implements IUseCase<String,List<LoanReturnValue>> {
    private final LoanRepositoryPort loanRepositoryPort;
    private final FindBookByIdAdapter findBookByIdAdapter;
    private final VerifyUserIdDifferentAdapter verifyUserIdDifferentAdapter;
    private final FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;

    @Override
    public List<LoanReturnValue> execute(String dniUser) throws MessagingException, IOException {
        List<Loan> loansFound = loanRepositoryPort.findLoanByDni(dniUser);
        if(loansFound.isEmpty()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOANS_FOUND.getMessage());
        }
        verifyUserIdDifferentAdapter.execute(loansFound);
        Long iduser = loansFound.getFirst().getUserId();
        UserMembershipInfo membershipInfo=findUserAndMembershipInfoByUserIdAdapter.execute(iduser);
        List<LoanReturnValue> loanReturns=new ArrayList<>();
        for (Loan loanProcess:loansFound){
            loanProcess.setReturnDate(LocalDate.now());
            Book bookFound=findBookByIdAdapter.execute(loanProcess.getBookId());
            Integer daysDelayed=calculateDelayedDays(loanProcess,membershipInfo);
            loanReturns.add(
                    LoanReturnValue.builder()
                            .idLoan(loanProcess.getId())
                            .title(bookFound.getTitle())
                            .isbn(bookFound.getIsbn())
                            .daysOverdue(daysDelayed)
                            .valueToPay(daysDelayed*membershipInfo.getDailyFine())
                            .build()
            );
        }
        loansFound=this.loanRepositoryPort.findLoanNoPaidByDni(dniUser);
        if(loansFound.isEmpty()){
            addLoansNoPaid(loanReturns,loansFound);
        }
        return loanReturns;
    }

    private void addLoansNoPaid(List<LoanReturnValue> loanReturns, List<Loan> loansFound) throws MessagingException, IOException {
        for (Loan loanProcess:loansFound){
            Book bookFound=findBookByIdAdapter.execute(loanProcess.getBookId());
            loanReturns.add(
                    LoanReturnValue.builder()
                            .idLoan(loanProcess.getId())
                            .title(bookFound.getTitle())
                            .isbn(bookFound.getIsbn())
                            .daysOverdue(loanProcess.getDaysDelayed())
                            .valueToPay(loanProcess.getFine())
                            .build()
            );
        }
    }

    private Integer calculateDelayedDays(Loan loan,UserMembershipInfo userMembershipInfoFound) {
        if(!loan.getReturnDate().isAfter(loan.getDueDate())){
            return 0;
        }
        int delayedDays= (int) (loan.getReturnDate().toEpochDay()-loan.getDueDate().toEpochDay());
        return delayedDays>userMembershipInfoFound.getGracePeriodDays()?(delayedDays- userMembershipInfoFound.getGracePeriodDays()):0;
    }

}
