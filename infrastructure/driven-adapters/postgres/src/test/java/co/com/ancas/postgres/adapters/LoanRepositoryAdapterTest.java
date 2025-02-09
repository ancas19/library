package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Loan;
import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.postgres.entities.LoanEntity;
import co.com.ancas.postgres.repositories.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanRepositoryAdapterTest {

    @Mock
    private LoanRepository loanRepository;
    @InjectMocks
    private LoanRepositoryAdapter loanRepositoryAdapter;
    private Loan loan;
    private LoanEntity loanEntity;
    private LoanInformation loanInformation;

    @BeforeEach
    void setUp(){
        loan= TestMock.loan();
        loanEntity= Mapper.map(loan, LoanEntity.class);
        loanInformation= TestMock.loanInformation();
    }

    @Test
    void save() {
        //Arrange
        when(loanRepository.save(any())).thenReturn(loanEntity);
        //Act
        Loan result= loanRepositoryAdapter.save(loan);
        //Assert
        assertNotNull(result);
    }

    @Test
    void countLoansActive() {
        //Arrange
        when(loanRepository.countLoansActive(anyLong(),any(LocalDate.class))).thenReturn(1);
        //Act
        Integer result= loanRepositoryAdapter.countLoansActive(1L);
        //Assert
        assertEquals(1, result);
    }

    @Test
    void findLoansByUser() {
        //Arrange
        Page<LoanInformation> page=new PageImpl<>(List.of(loanInformation));
        when(loanRepository.findLoansByUser(any(),any(),any(),any(),any())).thenReturn(page);
        //Act
        Page<LoanInformation> result= loanRepositoryAdapter.findLoansByUser(TestMock.loanSearchByUser());
        //Assert
        assertNotNull(result);
    }

    @Test
    void findReturnedLoansByUser(){
        //Arrange
        Page<LoanInformation> page=new PageImpl<>(List.of(loanInformation));
        when(loanRepository.findReturnedLoansByUser(any(),any(),any(),any(),any())).thenReturn(page);
        //Act
        Page<LoanInformation> result= loanRepositoryAdapter.findReturnedLoansByUser(TestMock.loanSearchByUser());
        //Assert
        assertNotNull(result);
    }

    @Test
    void findExpiredLoansByUser(){
        //Arrange
        Page<LoanInformation> page=new PageImpl<>(List.of(loanInformation));
        when(loanRepository.findExpiredLoansByUser(any(),any(),any(),any(),any(),any())).thenReturn(page);
        //Act
        Page<LoanInformation> result= loanRepositoryAdapter.findExpiredLoansByUser(TestMock.loanSearchByUser());
        //Assert
        assertNotNull(result);
    }

    @Test
    void findActiveLoansByUser(){
        //Arrange
        Page<LoanInformation> page=new PageImpl<>(List.of(loanInformation));
        when(loanRepository.findActiveLoansByUser(any(),any(),any(),any(),any())).thenReturn(page);
        //Act
        Page<LoanInformation> result= loanRepositoryAdapter.findActiveLoansByUser(TestMock.loanSearchByUser());
        //Assert
        assertNotNull(result);
    }

    @Test
    void existsByBookIdAndReturnDateIsNull(){
        //Arrange
        when(loanRepository.existsByBookIdAndUserIdAndReturnDateIsNull(anyLong(),anyLong())).thenReturn(true);
        //Act
        boolean result= loanRepositoryAdapter.existsByBookIdAndReturnDateIsNull(1L,1L);
        //Assert
        assertTrue(result);
    }

    @Test
    void findLoansByIds(){
        //Arrange
        when(loanRepository.findAllById(any())).thenReturn(List.of(loanEntity));
        //Act
        List<Loan> result= loanRepositoryAdapter.findLoansByIds(List.of(1L));
        //Assert
        assertNotNull(result);
    }

    @Test
    void existsLoansWithoutPaid(){
        //Arrange
        when(loanRepository.existsLoansWithoutPaid(anyLong())).thenReturn(1);
        //Act
        boolean result= loanRepositoryAdapter.existsLoansWithoutPaid(1L);
        //Assert
        assertTrue(result);
    }

    @Test
    void existsExpiredLoans(){
        //Arrange
        when(loanRepository.existsExpiredLoans(anyLong(),any(LocalDate.class))).thenReturn(1);
        //Act
        boolean result= loanRepositoryAdapter.existsExpiredLoans(1L);
        //Assert
        assertTrue(result);
    }

    @Test
    void findLoanByDni(){
        //Arrange
        when(loanRepository.findLoanByDni(any())).thenReturn(List.of(loanEntity));
        //Act
        List<Loan> result= loanRepositoryAdapter.findLoanByDni("123");
        //Assert
        assertNotNull(result);
    }

    @Test
    void findLoanNoPaidByDni(){
        //Arrange
        when(loanRepository.findLoanNoPaidByDni(any())).thenReturn(List.of(loanEntity));
        //Act
        List<Loan> result= loanRepositoryAdapter.findLoanNoPaidByDni("123");
        //Assert
        assertNotNull(result);
    }
}