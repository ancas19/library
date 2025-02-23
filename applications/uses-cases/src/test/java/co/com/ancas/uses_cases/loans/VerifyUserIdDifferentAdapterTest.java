package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.model.Loan;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VerifyUserIdDifferentAdapterTest {
    @InjectMocks
    private VerifyUserIdDifferentAdapter verifyUserIdDifferentAdapter;
    private Loan loan;

    @BeforeEach
    void setUp() {
        loan = TestMock.loan();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        // Act
       verifyUserIdDifferentAdapter.execute(List.of(loan));
        // Assert
        assertNotNull(loan);
    }

    @Test
    void executeWithDifferentUserId(){
        // Arrange
        loan.setReturnDate(LocalDate.now().plusDays(1));
        // Act and Assert
        assertThrows(Exception.class, () -> verifyUserIdDifferentAdapter.execute(List.of(loan)));
    }

    @Test
    void executeWithReturnDateNull(){
        // Arrange
        Loan loan1= TestMock.loan();
        loan1.setUserId(2L);
        // Act and Assert
        assertThrows(Exception.class, () -> verifyUserIdDifferentAdapter.execute(List.of(loan, loan1)));
    }

}