package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.BookCreationRequest;
import co.com.ancas.response.BookInformationResponse;
import co.com.ancas.service.BooksAppservice;
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
class BooksControllerTest {
    @Mock
    private BooksAppservice booksAppservice;
    @InjectMocks
    private BooksController booksController;
    private ObjectMapper objectMapper;
    private MockMvc mockMvc;
    private String url="/v1.0/books";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(booksController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void createBook() throws Exception {
        // Arrange
        when(booksAppservice.createBook(any(BookCreationRequest.class))).thenReturn(new BookInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(RequestMocks.bookCreationRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void findBookById() throws Exception {
        // Arrange
        when(booksAppservice.findBookById(1L)).thenReturn(new BookInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(get(url+"/1"));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    void findBooks() throws Exception {
        // Arrange
        Page<BookInformation> page=new PageImpl<>(List.of(TestMock.bookInformation()));
        when(booksAppservice.findBooksByCriteria(any(),any(),any())).thenReturn(Pagination.getPaginationResponse(page, BookInformationResponse.class));
        // Act
        ResultActions response=mockMvc.perform(post(url+"/all")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(RequestMocks.bookSearchCriteriaRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateBookInformation() throws Exception {
        // Arrange
        when(booksAppservice.updateBookInformation(any())).thenReturn(new BookInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(put(url+"/information")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(RequestMocks.bookUpdateRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateImageBook() throws Exception {
        // Arrange
        when(booksAppservice.updateImageBook(any())).thenReturn(new BookInformationResponse());
        // Act
        ResultActions response=mockMvc.perform(put(url+"/image")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(RequestMocks.imageUploadRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteBook() throws Exception {
        // Arrange
        doNothing().when(booksAppservice).changeBookStatus(any());

        // Act
        ResultActions response=mockMvc.perform(delete(url+"/1"));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void uploadFiles() throws Exception {
        // Arrange
        doNothing().when(booksAppservice).uploadBooksFiles(any());
        // Act
        ResultActions response=mockMvc.perform(post(url+"/files")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(RequestMocks.fileRequest())));
        // Assert
        response.andExpect(status().isOk())
                .andDo(print());
    }
}