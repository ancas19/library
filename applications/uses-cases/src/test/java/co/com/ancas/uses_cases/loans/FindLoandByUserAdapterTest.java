package co.com.ancas.uses_cases.loans;

import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.model.LoanSearchByUser;
import co.com.ancas.models.repositories.LoanRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindLoandByUserAdapterTest {
    @Mock
    private  LoanRepositoryPort loanRepositoryPort;
    @InjectMocks
    private FindLoandByUserAdapter findLoandByUserAdapter;
    private LoanInformation loanInformation;
    private LoanSearchByUser loanSearchByUser;

    @BeforeEach
    void setUp() {
        loanSearchByUser = TestMock.loanSearchByUser();
        loanInformation = TestMock.loanInformation();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        when(loanRepositoryPort.findActiveLoansByUser(loanSearchByUser)).thenReturn(new PageImpl<>(List.of(loanInformation)));
        // Act
        Page<LoanInformation> result = findLoandByUserAdapter.execute(loanSearchByUser);
        // Assert
        assertNotNull(result);
    }

    @Test
    void executeNotFound() {
        // Arrange
        when(loanRepositoryPort.findActiveLoansByUser(loanSearchByUser)).thenReturn(Page.empty());
        // Act and Assert
        assertThrows(Exception.class, () -> findLoandByUserAdapter.execute(loanSearchByUser));
    }

    
}