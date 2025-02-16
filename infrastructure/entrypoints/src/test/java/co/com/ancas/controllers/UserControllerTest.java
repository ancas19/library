package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.response.UserInformationResponse;
import co.com.ancas.service.UserAppService;
import co.com.ancas.utils.RequestMocks;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    private UserAppService userAppService;
    @InjectMocks
    private UserController userController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String url="/v1.0/users";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }


    @Test
    void changePassword() throws Exception {
        // Arrange
        doNothing().when(userAppService).updatePassword(any());
        // Act
        ResultActions response=mockMvc.perform(patch(url+"/password")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.changePasswordRequest())));
        // Assert
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void findUserByPersonId() throws Exception{
        // Arrange
        when(userAppService.findUserByPersonid(any())).thenReturn(new UserInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(get(url+"/1/people"));
        // Assert
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateUserMembership() throws Exception {
        // Arrange
        doNothing().when(userAppService).updateUserMembership(any());
        // Act
        ResultActions response=mockMvc.perform(patch(url+"/memberships")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.dniRequest())));
        // Assert
        response.andDo(print())
                .andExpect(status().isOk());
    }

}