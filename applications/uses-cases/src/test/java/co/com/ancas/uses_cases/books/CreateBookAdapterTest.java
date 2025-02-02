package co.com.ancas.uses_cases.books;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.authors.FindAuthorByFullNameAdapter;
import co.com.ancas.uses_cases.genres.FindGenreByValueAdapter;
import co.com.ancas.uses_cases.images.UploadImageAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateBookAdapterTest {
    @Mock
    private  BookRepositoryPort bookRepositoryPort;
    @Mock
    private  FindAuthorByFullNameAdapter findAuthorByFullNameAdapter;
    @Mock
    private  UploadImageAdapter uploadImageAdapter;
    @Mock
    private  FindGenreByValueAdapter findGenreByValueAdapter;
    @InjectMocks
    private CreateBookAdapter createBookAdapter;
    private BookCreation bookCreation;
    private Genre genre;
    private Author author;
    private Image image;
    private Book book;

    @BeforeEach
    void setUp() {
        genre = TestMock.genre();
        author = TestMock.author();
        bookCreation = TestMock.bookCreation();
        author.setFullName(bookCreation.getAuthor());
        image = TestMock.image();
        book = TestMock.book();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(bookRepositoryPort.existsByIsbn(anyString())).thenReturn(false);
        when(bookRepositoryPort.existsByTitle(anyString())).thenReturn(false);
        when(this.findGenreByValueAdapter.execute(anyString())).thenReturn(genre);
        when(this.findAuthorByFullNameAdapter.execute(anyString())).thenReturn(author);
        when(this.uploadImageAdapter.execute(any())).thenReturn(image);
        when(this.bookRepositoryPort.save(any())).thenReturn(book);
        //Act
        BookInformation bookInformation = createBookAdapter.execute(bookCreation);
        //Assert
        assertNotNull(bookInformation);
    }

    @Test
    void executeIsbnExists() {
        //Arrange
        when(bookRepositoryPort.existsByIsbn(anyString())).thenReturn(true);
        //Act & Assert
        assertThrows(BadRequestException.class, () -> createBookAdapter.execute(bookCreation));
    }

    @Test
    void executeTitleExists() {
        //Arrange
        when(bookRepositoryPort.existsByIsbn(anyString())).thenReturn(false);
        when(bookRepositoryPort.existsByTitle(anyString())).thenReturn(true);
        //Act & Assert
        assertThrows(BadRequestException.class, () -> createBookAdapter.execute(bookCreation));
    }
}