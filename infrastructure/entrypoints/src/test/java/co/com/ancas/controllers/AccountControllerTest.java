package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.PeopleRequest;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.service.PeopleAppService;
import co.com.ancas.utils.RequestMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static co.com.ancas.models.enums.Messages.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {
    @Mock
    private PeopleAppService peopleAppService;
    @InjectMocks
    private AccountController accountController;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private String url="/v1.0/account";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(accountController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void createPeople() throws Exception {
        // Arrange
        when(peopleAppService.createPeople(any(PeopleRequest.class))).thenReturn(Mapper.map(TestMock.people(), PeopleResponse.class));
        // Act
        ResultActions response=mockMvc.perform(post(url+"/sign-up")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.peopleRequest())));
        // Assert
        response.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void sendCodeToUnblockPeople() throws Exception {
        // Arrange
        doNothing().when(peopleAppService).sendCodeToUnblockPeople(any());
        // Act
        ResultActions response=mockMvc.perform(post(url+"/code")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.personAccessRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_SEND_CODE.getMessage()))
                .andDo(print());
    }

    @Test
    void unblockPeople() throws Exception {
        // Arrange
        doNothing().when(peopleAppService).unblockPeople(any());
        // Act
        ResultActions response=mockMvc.perform(post(url+"/access")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.personCodeRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_PEOPLE_UNBLOCKED.getMessage()))
                .andDo(print());
    }


    @Test
    void changePassword() throws Exception {
        // Arrange
        doNothing().when(peopleAppService).recoveryPassword(any());
        // Act
        ResultActions response=mockMvc.perform(put(url+"/passwords")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.passwordRecoveryRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(MESSAGE_PASSWORD_CHANGED.getMessage()))
                .andDo(print());
    }
}