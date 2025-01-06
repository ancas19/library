package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.ForbiddenException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.uses_cases.books.FindBookByIdAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import co.com.ancas.uses_cases.user.CurrentUserAdapter;
import co.com.ancas.uses_cases.user.FindUserAndMembershipInfoByUserIdAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CalculateValueToPayAdapter  implements IUseCase<List<Long>,List<LoanReturnValue>> {
    private final CurrentUserAdapter currentUserAdapter;
    private final LoanRepositoryPort loanRepositoryPort;
    private final FindBookByIdAdapter findBookByIdAdapter;
    private final FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;

    @Override
    public List<LoanReturnValue> execute(List<Long> idLoans) throws MessagingException, IOException {
        List<Loan> loansFound = loanRepositoryPort.findLoansByIds(idLoans);
        if(loansFound.isEmpty() || loansFound.size()!=idLoans.size()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOANS_FOUND.getMessage());
        }
        verifyUserIdDifferent(loansFound);
        Long iduser = loansFound.getFirst().getUserId();
        verifyCurrentUserId(iduser);
        UserMembershipInfo membershipInfo=findUserAndMembershipInfoByUserIdAdapter.execute(iduser);
        List<LoanReturnValue> loanReturns=new ArrayList<>();
        for (Loan loanProcess:loansFound){
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
        return loanReturns;
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

    private Integer calculateDelayedDays(Loan loan,UserMembershipInfo userMembershipInfoFound) {
        if(!loan.getReturnDate().isAfter(loan.getDueDate())){
            return 0;
        }
        int delayedDays= (int) (loan.getReturnDate().toEpochDay()-loan.getDueDate().toEpochDay());
        return delayedDays>userMembershipInfoFound.getGracePeriodDays()?delayedDays:0;
    }

}
