package co.com.ancas.uses_cases.books;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeBookStatusAdapterTest {
    @Mock
    private  BookRepositoryPort bookRepositoryPort;
    @Mock
    private  FindBookByIdAdapter findBookByIdAdapter;
    @InjectMocks
    private ChangeBookStatusAdapter changeBookStatusAdapter;
    private Book book;
    private ArgumentCaptor<Book> bookCaptor;

    @BeforeEach
    void setUp() {
        book = TestMock.book();
        bookCaptor = ArgumentCaptor.forClass(Book.class);
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findBookByIdAdapter.execute(any())).thenReturn(book);
        when(bookRepositoryPort.save(bookCaptor.capture())).thenReturn(book);
        //Act
        changeBookStatusAdapter.execute(1L);
        //Assert
        assertEquals("NO", bookCaptor.getValue().getAvailable());
    }
}