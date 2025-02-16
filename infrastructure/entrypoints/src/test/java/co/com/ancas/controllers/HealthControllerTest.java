package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class HealthControllerTest {
     @InjectMocks
     private HealthController healthController;
     private MockMvc mockMvc;
     private String url="/health";

     @BeforeEach
     void setUp() {
         mockMvc = MockMvcBuilders.standaloneSetup(healthController)
                 .setControllerAdvice(new CustomExceptionHandler())
                 .build();
     }

     @Test
        void health() throws Exception {
            // Arrange
            // Act
         ResultActions response=mockMvc.perform(get(url)
                 .contentType("application/json"));
            // Assert
            response.andDo(print())
                    .andExpect(status().isOk());
        }

}