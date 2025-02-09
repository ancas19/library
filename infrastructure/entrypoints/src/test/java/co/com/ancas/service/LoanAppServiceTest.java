package co.com.ancas.service;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.LoanDetails;
import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.model.LoanReturnResult;
import co.com.ancas.models.model.LoanReturnValue;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.LoanRequest;
import co.com.ancas.request.LoanReturnRequest;
import co.com.ancas.request.LoanSearchByUserRequest;
import co.com.ancas.response.*;
import co.com.ancas.uses_cases.loans.CalculateValueToPayAdapter;
import co.com.ancas.uses_cases.loans.CreateLoanAdapter;
import co.com.ancas.uses_cases.loans.FindLoandByUserAdapter;
import co.com.ancas.uses_cases.loans.ReturnLoanAdapter;
import co.com.ancas.utils.RequestMocks;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoanAppServiceTest {
    @Mock
    private  CreateLoanAdapter createLoanAdapter;
    @Mock
    private  ReturnLoanAdapter returnLoanAdapter;
    @Mock
    private  CurrentUserAppService currentUserAppService;
    @Mock
    private  FindLoandByUserAdapter findLoandByUserAdapter;
    @Mock
    private  CalculateValueToPayAdapter calculateValueToPayAdapter;
    @InjectMocks
    private LoanAppService loanAppService;
    private LoanRequest loanRequest;
    private LoanInformation loanInformation;
    private LoanReturnRequest loanReturnRequest;
    private LoanSearchByUserRequest loanSearchByUserRequest;

    @BeforeEach
    void setUp() {
        loanRequest = RequestMocks.loanRequest();
        loanInformation= TestMock.loanInformation();
        loanReturnRequest=RequestMocks.loanReturnRequest();
        loanSearchByUserRequest=RequestMocks.loanSearchByUserRequest();
    }

    @Test
    void createLoan() throws MessagingException, IOException {
        //Arrange
        when(createLoanAdapter.execute(any())).thenReturn(List.of(new LoanDetails()));
        //Act
        List<LoanDetailsResponse> result = loanAppService.createLoan(loanRequest);
        //Assert
        assertNotNull(result);
    }

    @Test
    void calculateValueToPay() throws MessagingException, IOException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentUserDniAndRole(any());
        when(calculateValueToPayAdapter.execute(any())).thenReturn(List.of(new LoanReturnValue()));
        //Act
        List<LoanReturnValueResponse> result = loanAppService.calculateValueToPay("1234567890");
        //Assert
        assertNotNull(result);
    }

    @Test
    void returnLoan() throws MessagingException, IOException {
        //Arrange
        when(returnLoanAdapter.execute(any())).thenReturn(new LoanReturnResult());
        //Act
        LoanReturnResultResponse result = loanAppService.returnLoan(List.of(loanReturnRequest));
        //Assert
        assertNotNull(result);
    }

    @Test
    void findLoansByUser() throws MessagingException, IOException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentUserDniAndRole(any());
        when(findLoandByUserAdapter.execute(any())).thenReturn(new PageImpl<>(List.of(loanInformation)));
        //Act
        PaginationResponse<LoanInformationResponse> result = loanAppService.findLoansByUser(loanSearchByUserRequest, 1, 1);
        //Assert
        assertNotNull(result);
    }

    @Test
    void findLoansByUser1() throws MessagingException, IOException {
        //Arrange
        loanSearchByUserRequest.setStartDate(null);
        loanSearchByUserRequest.setFinishDate(null);
        doNothing().when(currentUserAppService).verifyCurrentUserDniAndRole(any());
        when(findLoandByUserAdapter.execute(any())).thenReturn(new PageImpl<>(List.of(loanInformation)));
        //Act
        PaginationResponse<LoanInformationResponse> result = loanAppService.findLoansByUser(loanSearchByUserRequest, 1, 1);
        //Assert
        assertNotNull(result);
    }


    @Test
    void  findLoandByUserException(){
        //Arrange
        loanSearchByUserRequest.setStartDate(LocalDate.now().minusMonths(1));
        loanSearchByUserRequest.setFinishDate(LocalDate.now());
        //Act and Assert
        assertThrows(BadRequestException.class, () -> loanAppService.findLoansByUser(loanSearchByUserRequest, 1, 1));

    }
}