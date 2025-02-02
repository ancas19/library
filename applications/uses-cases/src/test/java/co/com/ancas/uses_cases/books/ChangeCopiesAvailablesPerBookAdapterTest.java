package co.com.ancas.uses_cases.books;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.AvailableCopiesUpdate;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeCopiesAvailablesPerBookAdapterTest {
    @Mock
    private  BookRepositoryPort bookRepositoryPort;
    @Mock
    private  FindBookByIdAdapter findBookByIdAdapter;
    @InjectMocks
    private ChangeCopiesAvailablesPerBookAdapter changeCopiesAvailablesPerBookAdapter;
    private AvailableCopiesUpdate availableCopiesUpdate;
    private Book bookFound;
    private ArgumentCaptor<Book> bookCaptor;

    @BeforeEach
    void setUp() {
        availableCopiesUpdate= TestMock.availableCopiesUpdate();
        bookFound= TestMock.book();
        bookCaptor= ArgumentCaptor.forClass(Book.class);
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findBookByIdAdapter.execute(availableCopiesUpdate.getBookId())).thenReturn(bookFound);
        when(bookRepositoryPort.save(bookCaptor.capture())).thenReturn(bookFound);
        //Act
        changeCopiesAvailablesPerBookAdapter.execute(availableCopiesUpdate);
        //Assert
        assertEquals((bookFound.getAvailableCopies()), bookCaptor.getValue().getAvailableCopies());
    }

    @Test
    void executeFail() throws MessagingException, IOException {
        //Arrange
        availableCopiesUpdate.setCopies(20);
        when(findBookByIdAdapter.execute(availableCopiesUpdate.getBookId())).thenReturn(bookFound);
        //Act and Assert
        assertThrows(BadRequestException.class,()->changeCopiesAvailablesPerBookAdapter.execute(availableCopiesUpdate));
    }

    @Test
    void executeFailAdd() throws MessagingException, IOException {
        //Arrange
        availableCopiesUpdate.setAction(Constants.INCREASE);
        when(findBookByIdAdapter.execute(availableCopiesUpdate.getBookId())).thenReturn(bookFound);
        when(bookRepositoryPort.save(bookCaptor.capture())).thenReturn(bookFound);
        //Act
        changeCopiesAvailablesPerBookAdapter.execute(availableCopiesUpdate);
        //Assert
        assertEquals((bookFound.getAvailableCopies()), bookCaptor.getValue().getAvailableCopies());
    }


}