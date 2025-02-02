package co.com.ancas.uses_cases.books;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.BookUpdate;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.authors.FindAuthorByFullNameAdapter;
import co.com.ancas.uses_cases.genres.FindGenreByValueAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateBookInformationAdapterTest {

    @Mock
    private  FindBookByIdAdapter findBookByIdAdapter;
    @Mock
    private  BookRepositoryPort bookRepositoryPort;
    @Mock
    private  FindAuthorByFullNameAdapter findAuthorByFullNameAdapter;
    @Mock
    private  FindGenreByValueAdapter findGenreByValueAdapter;
    @Mock
    private  FindBookInformationByIdAdapter findBookInformationByIdAdapter;
    @InjectMocks
    private UpdateBookInformationAdapter updateBookInformationAdapter;
    private BookUpdate bookUpdate;
    private Book bookFound;
    @BeforeEach
    void setUp() {
        bookUpdate = TestMock.bookUpdate();
        bookFound = TestMock.book();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findBookByIdAdapter.execute(bookUpdate.getId())).thenReturn(bookFound);
        when(bookRepositoryPort.existsByIsbnAndIdNot(bookUpdate.getIsbn(), bookFound.getId())).thenReturn(false);
        when(bookRepositoryPort.existsByTitleAndIdNot(bookUpdate.getTitle(), bookFound.getId())).thenReturn(false);
        when(findGenreByValueAdapter.execute(bookUpdate.getGenre())).thenReturn(TestMock.genre());
        when(findAuthorByFullNameAdapter.execute(bookUpdate.getAuthor())).thenReturn(TestMock.author());
        when(findBookInformationByIdAdapter.execute(bookFound.getId())).thenReturn(TestMock.bookInformation());
        //Act
        BookInformation result = updateBookInformationAdapter.execute(bookUpdate);
        //Assert
        assertNotNull(result);

    }

    @Test
    void executeIsbnExists() throws MessagingException, IOException {
        //Arrange
        when(findBookByIdAdapter.execute(bookUpdate.getId())).thenReturn(bookFound);
        when(bookRepositoryPort.existsByIsbnAndIdNot(bookUpdate.getIsbn(), bookFound.getId())).thenReturn(true);
        //Act
        assertThrows(Exception.class, () -> updateBookInformationAdapter.execute(bookUpdate));
    }

    @Test
    void executeTitleExists() throws MessagingException, IOException {
        //Arrange
        when(findBookByIdAdapter.execute(bookUpdate.getId())).thenReturn(bookFound);
        when(bookRepositoryPort.existsByIsbnAndIdNot(bookUpdate.getIsbn(), bookFound.getId())).thenReturn(false);
        when(bookRepositoryPort.existsByTitleAndIdNot(bookUpdate.getTitle(), bookFound.getId())).thenReturn(true);
        //Act
        assertThrows(Exception.class, () -> updateBookInformationAdapter.execute(bookUpdate));
    }
}