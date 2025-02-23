package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.Loan;
import co.com.ancas.models.model.LoanReturn;
import co.com.ancas.models.model.LoanReturnResult;
import co.com.ancas.models.model.UserMembershipInfo;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.books.ChangeCopiesAvailablesPerBookAdapter;
import co.com.ancas.uses_cases.user.FindUserAndMembershipInfoByUserIdAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnLoanAdapterTest {
    @Mock
    private  LoanRepositoryPort loanRepositoryPort;
    @Mock
    private  VerifyUserIdDifferentAdapter verifyUserIdDifferentAdapter;
    @Mock
    private  ChangeCopiesAvailablesPerBookAdapter changeCopiesAvailablesPerBookAdapter;
    @Mock
    private  FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;
    @InjectMocks
    private ReturnLoanAdapter returnLoanAdapter;
    private LoanReturn loanReturn;
    private Loan loan;
    private UserMembershipInfo userMembershipInfo;

    @BeforeEach
    void setUp() {
        loanReturn = TestMock.loanReturn();
        loan = TestMock.loan();
        userMembershipInfo = TestMock.userMembershipInfo();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(loanRepositoryPort.findLoansByIds(any())).thenReturn(List.of(loan));
        doNothing().when(verifyUserIdDifferentAdapter).execute(any());
        when(findUserAndMembershipInfoByUserIdAdapter.execute(anyLong())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.save(any())).thenReturn(loan);
        doNothing().when(changeCopiesAvailablesPerBookAdapter).execute(any());
        //Act
        LoanReturnResult result = returnLoanAdapter.execute(List.of(loanReturn));
        //Assert
        assertNotNull(result);
    }

    @Test
    void execute1() throws MessagingException, IOException {
        //Arrange
        loan.setDueDate(LocalDate.now());
        loanReturn.setFine(0.0);
        when(loanRepositoryPort.findLoansByIds(any())).thenReturn(List.of(loan));
        doNothing().when(verifyUserIdDifferentAdapter).execute(any());
        when(findUserAndMembershipInfoByUserIdAdapter.execute(anyLong())).thenReturn(userMembershipInfo);
        when(loanRepositoryPort.save(any())).thenReturn(loan);
        doNothing().when(changeCopiesAvailablesPerBookAdapter).execute(any());
        //Act
        LoanReturnResult result = returnLoanAdapter.execute(List.of(loanReturn));
        //Assert
        assertNotNull(result);
    }

    @Test
    void executeException() throws MessagingException, IOException {
        //Arrange
        loan.setId(5L);
        when(loanRepositoryPort.findLoansByIds(any())).thenReturn(List.of(loan));
        doNothing().when(verifyUserIdDifferentAdapter).execute(any());
        when(findUserAndMembershipInfoByUserIdAdapter.execute(anyLong())).thenReturn(userMembershipInfo);
        //Act and Assert
        assertThrows(NotFoundException.class,()->returnLoanAdapter.execute(List.of(loanReturn)));
    }

    @Test
    void executeException1() throws MessagingException, IOException {
        //Arrange
        loanReturn.setFine(333333.0);
        when(loanRepositoryPort.findLoansByIds(any())).thenReturn(List.of(loan));
        doNothing().when(verifyUserIdDifferentAdapter).execute(any());
        when(findUserAndMembershipInfoByUserIdAdapter.execute(anyLong())).thenReturn(userMembershipInfo);
        //Act and Assert
        assertThrows(BadRequestException.class,()->returnLoanAdapter.execute(List.of(loanReturn)));
    }

    @Test
    void executeException2()  {
        //Arrange
        when(loanRepositoryPort.findLoansByIds(any())).thenReturn(Collections.emptyList());
        //Act and Assert
        assertThrows(BadRequestException.class,()->returnLoanAdapter.execute(List.of(loanReturn)));
    }
}