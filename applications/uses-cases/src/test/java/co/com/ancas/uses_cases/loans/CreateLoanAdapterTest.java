package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.books.ChangeCopiesAvailablesPerBookAdapter;
import co.com.ancas.uses_cases.books.FindBookByIsbnAdapter;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.user.FindUserAndMembershipInfoAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateLoanAdapterTest {

    @Mock
    private  FindUserAndMembershipInfoAdapter findUserAndMembershipInfoAdapter;
    @Mock
    private  FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    @Mock
    private  ChangeCopiesAvailablesPerBookAdapter changeCopiesAvailablesPerBookAdapter;
    @Mock
    private  EmailRepositoryPort emailRepositoryPort;
    @Mock
    private  FindBookByIsbnAdapter findBookByIsbnAdapter;
    @Mock
    private  LoanRepositoryPort loanRepositoryPort;
    @InjectMocks
    private CreateLoanAdapter createLoanAdapter;
    private UserMembershipInfo userMembershipInfo;
    private Book book;
    private LoanCreation loanCreation;
    private Loan loan;

    @BeforeEach
    void setUp() {
        userMembershipInfo = TestMock.userMembershipInfo();
        book = TestMock.book();
        loanCreation = TestMock.loanCreation();
        loan = TestMock.loan();
    }
    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findUserAndMembershipInfoAdapter.execute(anyString())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.existsExpiredLoans(anyLong())).thenReturn(false);
        when(loanRepositoryPort.existsLoansWithoutPaid(anyLong())).thenReturn(false);
        when(loanRepositoryPort.countLoansActive(anyLong())).thenReturn(0);
        when(findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("template");
        when(findBookByIsbnAdapter.execute(anyString())).thenReturn(book);
        doNothing().when(changeCopiesAvailablesPerBookAdapter).execute(any());
        when(this.loanRepositoryPort.save(any())).thenReturn(loan);
        when(loanRepositoryPort.existsByBookIdAndReturnDateIsNull(anyLong(),anyLong())).thenReturn(false);
        doNothing().when(emailRepositoryPort).sendEmail(any());
        //Act
        List<LoanDetails> loanDetailsCreated= createLoanAdapter.execute(loanCreation);
        //Assert
        assertNotNull(loanDetailsCreated);
    }

    @Test
    void executeException() throws MessagingException, IOException {
        //Arrange
        when(findUserAndMembershipInfoAdapter.execute(anyString())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.existsExpiredLoans(anyLong())).thenReturn(false);
        when(loanRepositoryPort.existsLoansWithoutPaid(anyLong())).thenReturn(false);
        when(loanRepositoryPort.countLoansActive(anyLong())).thenReturn(0);
        when(findBookByIsbnAdapter.execute(anyString())).thenReturn(book);
        when(loanRepositoryPort.existsByBookIdAndReturnDateIsNull(anyLong(),anyLong())).thenReturn(true);
        //Act and Assert
        assertThrows(BadRequestException.class,()->createLoanAdapter.execute(loanCreation));
    }

    @Test
    void executeException2() throws MessagingException, IOException {
        //Arrange
        book.setAvailableCopies(0);
        when(findUserAndMembershipInfoAdapter.execute(anyString())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.existsExpiredLoans(anyLong())).thenReturn(false);
        when(loanRepositoryPort.existsLoansWithoutPaid(anyLong())).thenReturn(false);
        when(loanRepositoryPort.countLoansActive(anyLong())).thenReturn(0);
        when(findBookByIsbnAdapter.execute(anyString())).thenReturn(book);
        //Act and Assert
        assertThrows(BadRequestException.class,()->createLoanAdapter.execute(loanCreation));
    }

    @Test
    void executeException3() throws MessagingException, IOException {
        //Arrange
        book.setAvailableCopies(0);
        loanCreation.getDetails().add(new LoanInfo());
        when(findUserAndMembershipInfoAdapter.execute(anyString())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.existsExpiredLoans(anyLong())).thenReturn(false);
        when(loanRepositoryPort.existsLoansWithoutPaid(anyLong())).thenReturn(false);
        when(loanRepositoryPort.countLoansActive(anyLong())).thenReturn(4);
        //Act and Assert
        assertThrows(BadRequestException.class,()->createLoanAdapter.execute(loanCreation));
    }

    @Test
    void executeException4() throws MessagingException, IOException {
        //Arrange
        when(findUserAndMembershipInfoAdapter.execute(anyString())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.existsExpiredLoans(anyLong())).thenReturn(false);
        when(loanRepositoryPort.existsLoansWithoutPaid(anyLong())).thenReturn(false);
        when(loanRepositoryPort.countLoansActive(anyLong())).thenReturn(5);
        //Act and Assert
        assertThrows(BadRequestException.class,()->createLoanAdapter.execute(loanCreation));
    }

    @Test
    void executeException5() throws MessagingException, IOException {
        //Arrange
        when(findUserAndMembershipInfoAdapter.execute(anyString())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.existsExpiredLoans(anyLong())).thenReturn(false);
        when(loanRepositoryPort.existsLoansWithoutPaid(anyLong())).thenReturn(true);
        //Act and Assert
        assertThrows(BadRequestException.class,()->createLoanAdapter.execute(loanCreation));
    }

    @Test
    void executeException6() throws MessagingException, IOException {
        //Arrange
        when(findUserAndMembershipInfoAdapter.execute(anyString())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.existsExpiredLoans(anyLong())).thenReturn(true);
        //Act and Assert
        assertThrows(BadRequestException.class,()->createLoanAdapter.execute(loanCreation));
    }
}