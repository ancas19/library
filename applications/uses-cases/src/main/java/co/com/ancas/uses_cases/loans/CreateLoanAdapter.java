package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.uses_cases.books.FindBookByIsbnAdapter;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import co.com.ancas.uses_cases.user.FindUserAndMembershipInfoAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateLoanAdapter implements IUseCase<LoanCreation,List<LoanDetails>> {
    private final FindUserAndMembershipInfoAdapter findUserAndMembershipInfoAdapter;
    private final FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    private final EmailRepositoryPort emailRepositoryPort;
    private final FindBookByIsbnAdapter findBookByIsbnAdapter;
    private final LoanRepositoryPort loanRepositoryPort;


    @Override
    public List<LoanDetails> execute(LoanCreation loanCreation) throws MessagingException, IOException {
        UserMembershipInfo userMembershipInfoFound = findUserAndMembershipInfoAdapter.execute(loanCreation.getDni());
        Integer loansActive = loanRepositoryPort.countLoansActive(userMembershipInfoFound.getUserId());
        if(loansActive>=userMembershipInfoFound.getLoanLimit()){
            throw new MessagingException(Messages.MESSAGE_ERROR_LOAN_LIMIT.getMessage());
        }
        loansActive+=loanCreation.getDetails().size();
        if (loansActive>userMembershipInfoFound.getLoanLimit()){
            throw new MessagingException(Messages.MESSAGE_ERROR_LOAN_LIMIT.getMessage());
        }
        List<LoanDetails> loansCreated= new ArrayList<>();
        for (LoanInfo loanInfo: loanCreation.getDetails()){
            LoanDetails loanDetails =processLoan(loanInfo, userMembershipInfoFound);
            loansCreated.add(loanDetails);
        }
        String templateFound=findEmailTemplateBySubjectAdapter.execute(Constants.BOOK_LOAN.getConstant());
        templateFound=populateTemplate(templateFound, loansCreated);
        this.emailRepositoryPort.sendEmail(
                Email.builder()
                        .recipient(templateFound)
                        .subject(Constants.BOOKS_LOAN.getConstant())
                        .body(templateFound)
                        .build()
        );
        return loansCreated;
    }

    private LoanDetails processLoan(LoanInfo loanInfo, UserMembershipInfo userMembershipInfoFound) throws MessagingException, IOException {
        Book bookFound =findBookByIsbnAdapter.execute(loanInfo.getIsbn());
        LocalDate loanDate =loanInfo.getLoanDate();
        LocalDate dueDate = calculateReturnDate(loanDate, userMembershipInfoFound.getLoanPeriodDays());
        Loan loanCreated=this.loanRepositoryPort.save(
                Loan.builder()
                        .userId(userMembershipInfoFound.getUserId())
                        .bookId(bookFound.getId())
                        .loanDate(loanDate)
                        .dueDate(dueDate)
                        .build()
        );
        return LoanDetails.builder()
                .id(loanCreated.getId())
                .loanDate(loanDate)
                .returnDate(dueDate)
                .daysDelayed(userMembershipInfoFound.getLoanPeriodDays())
                .isbn(bookFound.getIsbn())
                .title(bookFound.getTitle())
                .build();
    }

    private LocalDate calculateReturnDate(LocalDate loanDate, Integer loanDays){
        if(loanDays <= 0){
            return loanDate;
        }
        Integer countDays = 0;
        while (countDays<loanDays){
            loanDate = loanDate.plusDays(1);
            if(loanDate.getDayOfWeek().getValue()<=5){
                countDays++;
            }
        }
        return loanDate;
    }

    private String populateTemplate(String template, List<LoanDetails> loansCreated) {
        StringBuilder booksHtml = new StringBuilder();
        for (LoanDetails book : loansCreated) {
            booksHtml.append("<tr>");
            booksHtml.append("<td>").append(book.getTitle()).append("</td>");
            booksHtml.append("<td>").append(book.getLoanDate()).append("</td>");
            booksHtml.append("<td>").append(book.getReturnDate()).append("</td>");
            booksHtml.append("</tr>");
        }
        return template.replace(Constants.REPLACE_LOANS.getConstant(), booksHtml.toString());
    }
}
