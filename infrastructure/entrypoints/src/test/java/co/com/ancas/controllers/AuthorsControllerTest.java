package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.AuthorCreationRequest;
import co.com.ancas.response.AuthorInformationResponse;
import co.com.ancas.service.AuthorAppService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthorsControllerTest {

    @Mock
    private AuthorAppService authorAppService;
    @InjectMocks
    private AuthorsController authorsController;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private String url="/v1.0/authors";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(authorsController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void createAuthpr() throws Exception {
        // Arrange
        when(authorAppService.createAuthor(any(AuthorCreationRequest.class))).thenReturn(new AuthorInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(post(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.authorCreationRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findAuthors() throws Exception {
        // Arrange
        Page<AuthorInformation> page=new PageImpl<>(List.of(TestMock.authorInformation()));
        when(authorAppService.findAuthors(any(), any(), any())).thenReturn(Pagination.getPaginationResponse(page, AuthorInformationResponse.class));
        // Act
        ResultActions response=mockMvc.perform(post(url+"/all")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.searchParameterRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateAuthor() throws Exception {
        // Arrange
        when(authorAppService.updateAuthorInformation(any())).thenReturn(new AuthorInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(put(url+"/information")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.authorInformationRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateImageAuthor() throws Exception {
        // Arrange
        when(authorAppService.updateImageAuthor(any())).thenReturn(new AuthorInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(put(url+"/image")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.imageUploadRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        // Arrange
        when(authorAppService.findById(any())).thenReturn(new AuthorInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(get(url+"/1")
                .contentType("application/json"));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void uploadFiles() throws Exception {
        // Arrange
        doNothing().when(authorAppService).uploadAuthorsByFile(any());
        // Act
        ResultActions response=mockMvc.perform(post(url+"/files")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.fileRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }
}