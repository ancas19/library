package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.service.GenresAppService;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class GenresControllerTest {

    @Mock
    private GenresAppService genresAppService;
    @InjectMocks
    private GenresController genresController;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private String url="/v1.0/genres";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(genresController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void findAll() throws Exception {
        // Arrange
        when(genresAppService.findAll()).thenReturn(List.of("Fantasy", "Science Fiction"));
        // Act
        ResultActions response=mockMvc.perform(get(url)
                .contentType("application/json"));
        // Assert
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray());
    }
}