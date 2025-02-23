package co.com.ancas.service;

import co.com.ancas.models.model.BookCreation;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.BookCreationRequest;
import co.com.ancas.request.BookSearchCriteriaRequest;
import co.com.ancas.response.BookInformationResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.uses_cases.books.*;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BooksAppserviceTest {
    @Mock
    private  CreateBookAdapter createBookAdapter;
    @Mock
    private  FindBooksByCriteriaAdapter findBooksByCriteriaAdapter;
    @Mock
    private  FindBookInformationByIdAdapter findBookByIdAdapter;
    @Mock
    private  UpdateImagenBookAdapter updateImagenBookAdapter;
    @Mock
    private  UpdateBookInformationAdapter updateBookInformationAdapter;
    @Mock
    private  ChangeBookStatusAdapter changeBookStatusAdapter;
    @Mock
    private  UploadBooksByFileAdapter uploadBooksFilesAdapter;
    @InjectMocks
    private BooksAppservice booksAppservice;
    private BookCreationRequest bookCreationRequest;
    private BookInformation bookInformation;
    private BookSearchCriteriaRequest bookSearchCriteriaRequest;

    @BeforeEach
    void setUp() {
        bookCreationRequest = RequestMocks.bookCreationRequest();
        bookInformation = TestMock.bookInformation();
        bookSearchCriteriaRequest = RequestMocks.bookSearchCriteriaRequest();
    }

    @Test
    void createBook() throws MessagingException, IOException {
        //Arrange
        when(createBookAdapter.execute(any(BookCreation.class))).thenReturn(bookInformation);
        //Act
        BookInformationResponse bookInformationResponse = booksAppservice.createBook(bookCreationRequest);
        //Assert
        assertNotNull(bookInformationResponse);
    }

    @Test
    void findBooksByCriteria() throws MessagingException, IOException {
        //Arrange
        when(findBooksByCriteriaAdapter.execute(any())).thenReturn(new PageImpl<>(List.of(bookInformation)));
        //Act
        PaginationResponse<BookInformationResponse> response=booksAppservice.findBooksByCriteria(bookSearchCriteriaRequest, 1, 1);
        //Assert
        assertNotNull(response);
    }

    @Test
    void findBookById() {
        //Arrange
        when(findBookByIdAdapter.execute(any())).thenReturn(bookInformation);
        //Act
        BookInformationResponse bookInformationResponse = booksAppservice.findBookById(1L);
        //Assert
        assertNotNull(bookInformationResponse);
    }

    @Test
    void updateBookInformation() throws MessagingException, IOException {
        //Arrange
        when(updateBookInformationAdapter.execute(any())).thenReturn(bookInformation);
        //Act
        BookInformationResponse bookInformationResponse = booksAppservice.updateBookInformation(RequestMocks.bookUpdateRequest());
        //Assert
        assertNotNull(bookInformationResponse);
    }

    @Test
    void updateImageBook() throws MessagingException, IOException {
        //Arrange
        when(updateImagenBookAdapter.execute(any())).thenReturn(bookInformation);
        //Act
        BookInformationResponse bookInformationResponse = booksAppservice.updateImageBook(RequestMocks.imageUploadRequest());
        //Assert
        assertNotNull(bookInformationResponse);
    }

    @Test
    void changeBookStatus() throws MessagingException, IOException {
        //Arrange
        doNothing().when(changeBookStatusAdapter).execute(any());
        //Act
        booksAppservice.changeBookStatus(1L);
        //Assert
        verify(changeBookStatusAdapter, times(1)).execute(1L);
    }

    @Test
    void uploadBooksFiles() throws MessagingException, IOException {
        //Arrange
        doNothing().when(uploadBooksFilesAdapter).execute(any());
        //Act
        booksAppservice.uploadBooksFiles(RequestMocks.fileRequest());
        //Assert
        verify(uploadBooksFilesAdapter, times(1)).execute(any());
    }

}