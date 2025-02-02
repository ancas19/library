package co.com.ancas.uses_cases.books;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookByIsbnAdapterTest {
    @Mock
    private BookRepositoryPort bookRepositoryPort;
    @InjectMocks
    private FindBookByIsbnAdapter findBookByIsbnAdapter;
    private Book book;

    @BeforeEach
    void setUp() {
        book = TestMock.book();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
       when(bookRepositoryPort.findBookByIsbn(anyString())).thenReturn(Optional.of(book));
        //Act
        Book bookFound = findBookByIsbnAdapter.execute("1234567890");
        //Assert
        assertNotNull(bookFound);
    }

    @Test
    void executeNotFound() {
        //Arrange
        when(bookRepositoryPort.findBookByIsbn(anyString())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(Exception.class, () -> findBookByIsbnAdapter.execute("1234567890"));
    }
}