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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookByIdAdapterTest {
    @Mock
    private BookRepositoryPort bookRepositoryPort;
    @InjectMocks
    private FindBookByIdAdapter findBookByIdAdapter;
    private Book book;

    @BeforeEach
    void setUp() {
        book = TestMock.book();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(bookRepositoryPort.findById(anyLong())).thenReturn(Optional.of(book));
        //Act
        Book bookFound = findBookByIdAdapter.execute(1L);
        //Assert
        assertEquals(book, bookFound);
    }

    @Test
    void executeNotFound() {
        //Arrange
        when(bookRepositoryPort.findById(anyLong())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(Exception.class, () -> findBookByIdAdapter.execute(1L));
    }
}