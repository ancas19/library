package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.uses_cases.books.ChangeCopiesAvailablesPerBookAdapter;
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
    private final ChangeCopiesAvailablesPerBookAdapter changeCopiesAvailablesPerBookAdapter;
    private final EmailRepositoryPort emailRepositoryPort;
    private final FindBookByIsbnAdapter findBookByIsbnAdapter;
    private final LoanRepositoryPort loanRepositoryPort;


    @Override
    public List<LoanDetails> execute(LoanCreation loanCreation) throws MessagingException, IOException {
        UserMembershipInfo userMembershipInfoFound = findUserAndMembershipInfoAdapter.execute(loanCreation.getDni());
        boolean existsExpiredLoans=loanRepositoryPort.existsExpiredLoans(userMembershipInfoFound.getUserId());
        if(existsExpiredLoans){
            throw new BadRequestException(Messages.MESSAGE_ERROR_EXPIRED_LOANS.getMessage());
        }
        boolean existsLoansWihtoutPaid=loanRepositoryPort.existsLoansWithoutPaid(userMembershipInfoFound.getUserId());
        if(existsLoansWihtoutPaid) {
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOAN_NOT_RETURNED.getMessage());
        }
        Integer loansActive = loanRepositoryPort.countLoansActive(userMembershipInfoFound.getUserId());
        if(loansActive>=userMembershipInfoFound.getLoanLimit()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOAN_LIMIT.getMessage());
        }
        loansActive+=loanCreation.getDetails().size();
        if (loansActive>userMembershipInfoFound.getLoanLimit()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_LOAN_LIMIT.getMessage());
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
                        .recipient(List.of(userMembershipInfoFound.getEmail()))
                        .subject(Constants.BOOKS_LOAN.getConstant())
                        .body(templateFound)
                        .build()
        );
        return loansCreated;
    }

    private LoanDetails processLoan(LoanInfo loanInfo, UserMembershipInfo userMembershipInfoFound) throws MessagingException, IOException {
        Book bookFound =findBookByIsbnAdapter.execute(loanInfo.getIsbn());
        if(bookFound.getAvailableCopies()<=0){
            throw new BadRequestException(Messages.MESSAGE_ERROR_BOOK_NOT_AVAILABLE.getMessage().formatted("%s-%s".formatted(bookFound.getIsbn(),bookFound.getTitle())));
        }
        boolean isBookLoaned = loanRepositoryPort.existsByBookIdAndReturnDateIsNull(bookFound.getId(),userMembershipInfoFound.getUserId());
        if(isBookLoaned){
            throw new BadRequestException(Messages.MESSAGE_BOOK_ALREADY_LOANED.getMessage().formatted("%s-%s".formatted(bookFound.getIsbn(),bookFound.getTitle())));
        }
        LocalDate loanDate =loanInfo.getLoanDate();
        LocalDate dueDate = calculateReturnDate(loanDate, userMembershipInfoFound.getLoanPeriodDays());
        changeCopiesAvailablesPerBookAdapter.execute(
                AvailableCopiesUpdate.builder()
                        .bookId(bookFound.getId())
                        .action(Constants.DISCOUNT)
                        .copies(1)
                        .build()
        );
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
        int countDays = 0;
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
            booksHtml.append(Constants.OPEN_TR.getConstant());
            booksHtml.append(Constants.OPEN_TD.getConstant()).append(book.getIsbn()).append(Constants.CLOSE_TD.getConstant());
            booksHtml.append(Constants.OPEN_TD.getConstant()).append(book.getTitle()).append(Constants.CLOSE_TD.getConstant());
            booksHtml.append(Constants.OPEN_TD.getConstant()).append(book.getLoanDate()).append(Constants.CLOSE_TD.getConstant());
            booksHtml.append(Constants.OPEN_TD.getConstant()).append(book.getReturnDate()).append(Constants.CLOSE_TD.getConstant());
            booksHtml.append(Constants.CLOSE_TR.getConstant());
        }
        return template.replace(Constants.REPLACE_LOANS.getConstant(), booksHtml.toString());
    }
}
