package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.model.Loan;
import co.com.ancas.models.model.LoanReturnValue;
import co.com.ancas.models.model.UserMembershipInfo;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.books.FindBookByIdAdapter;
import co.com.ancas.uses_cases.user.FindUserAndMembershipInfoByUserIdAdapter;
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
class CalculateValueToPayAdapterTest {

    @Mock
    private  LoanRepositoryPort loanRepositoryPort;
    @Mock
    private  FindBookByIdAdapter findBookByIdAdapter;
    @Mock
    private  VerifyUserIdDifferentAdapter verifyUserIdDifferentAdapter;
    @Mock
    private  FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;
    @InjectMocks
    private CalculateValueToPayAdapter calculateValueToPayAdapter;
    private Loan loan;
    private UserMembershipInfo userMembershipInfo;
    private Book book;

    @BeforeEach
    void setUp() {
        loan= TestMock.loan();
        userMembershipInfo=TestMock.userMembershipInfo();
        book=TestMock.book();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(loanRepositoryPort.findLoanByDni(anyString())).thenReturn(List.of(loan));
        doNothing().when(verifyUserIdDifferentAdapter).execute(anyList());
        when(findUserAndMembershipInfoByUserIdAdapter.execute(anyLong())).thenReturn(userMembershipInfo);
        when(findBookByIdAdapter.execute(anyLong())).thenReturn(book);
        when(loanRepositoryPort.findLoanNoPaidByDni(anyString())).thenReturn(List.of(loan));
        //Act
        List<LoanReturnValue> result = calculateValueToPayAdapter.execute("123456789");
        //Assert
        assertNotNull(result);
        assertEquals(2,result.size());
    }

    @Test
    void executeEmptyLoansFound() {
        //Arrange
        when(loanRepositoryPort.findLoanByDni(anyString())).thenReturn(List.of());
        //Act
        //Assert
        assertThrows(Exception.class,()->calculateValueToPayAdapter.execute("123456789"));
    }
}