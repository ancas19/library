package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.AuthLoginRequest;
import co.com.ancas.response.AuthTokenResponse;
import co.com.ancas.service.AuthAppService;
import co.com.ancas.utils.RequestMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class AuthLoginControllerTest {
    @Mock
    private AuthAppService authService;
    @InjectMocks
    private AuthLoginController authLoginController;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private String url="/v1.0/auth";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(authLoginController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }


    @Test
    void login() throws Exception {
        // Arrange
        when(authService.login(any(AuthLoginRequest.class))).thenReturn(new AuthTokenResponse());
        // Act
        ResultActions response=mockMvc.perform(post(url+"/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.authLoginRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(Messages.MESSAGE_LOGIN_SUCCESSFUL.getMessage()))
                .andDo(print());
    }

    @Test
    void logout() throws Exception {
        // Arrange
        doNothing().when(authService).logout("token");
        // Act
        ResultActions response=mockMvc.perform(post(url+"/logout")
                .contentType("application/json")
                .param("Authorization","Bearer token"));
        // Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value(Messages.MESSAGE_LOGOUT_SUCCESSFUL.getMessage()))
                .andDo(print());
    }
}