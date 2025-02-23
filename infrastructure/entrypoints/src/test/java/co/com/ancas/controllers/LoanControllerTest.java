package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.LoanRequest;
import co.com.ancas.request.LoanSearchByUserRequest;
import co.com.ancas.response.LoanDetailsResponse;
import co.com.ancas.response.LoanInformationResponse;
import co.com.ancas.response.LoanReturnResultResponse;
import co.com.ancas.response.LoanReturnValueResponse;
import co.com.ancas.service.LoanAppService;
import co.com.ancas.utils.Pagination;
import co.com.ancas.utils.RequestMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LoanControllerTest {

    @Mock
    private LoanAppService loanAppService;
    @InjectMocks
    private LoanController loanController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String url = "/v1.0/loans";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(loanController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void createLoan() throws Exception {
        // Arrange
        when(loanAppService.createLoan(any(LoanRequest.class))).thenReturn(List.of(new LoanDetailsResponse()));
        // Act
        ResultActions response = mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.loanRequest())));
        // Assert
        response.andDo(print()).andExpect(status().isOk());
    }


    @Test
    void findLoansByUser() throws Exception {
        // Arrange
        Page<LoanInformation> loanInformationPage = new PageImpl<>(List.of(TestMock.loanInformation()));
        when(loanAppService.findLoansByUser(any(LoanSearchByUserRequest.class), any(Integer.class), any(Integer.class))).thenReturn(Pagination.getPaginationResponse(loanInformationPage, LoanInformationResponse.class));
        // Act
        ResultActions response = mockMvc.perform(post(url + "/users")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.loanSearchByUserRequest())));
        // Assert
        response.andDo(print()).andExpect(status().isOk());
    }


    @Test
    void calculateValueToPay() throws Exception {
        // Arrange
        when(loanAppService.calculateValueToPay(any())).thenReturn(List.of(new LoanReturnValueResponse()));
        // Act
        ResultActions response = mockMvc.perform(post(url + "/fee")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.dniRequest())));
        // Assert
        response.andDo(print()).andExpect(status().isOk());
    }

    @Test
    void returnLoan() throws Exception {
        // Arrange
        when(loanAppService.returnLoan(any())).thenReturn(new LoanReturnResultResponse());
        // Act
        ResultActions response = mockMvc.perform(patch(url + "/checkout-completion")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(List.of(RequestMocks.loanReturnRequest()))));
        // Assert
        response.andDo(print()).andExpect(status().isOk());
    }
}