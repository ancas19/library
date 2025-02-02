package co.com.ancas.uses_cases.books;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.BookSearchCriteria;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBooksByCriteriaAdapterTest {

    @Mock
    private BookRepositoryPort bookRepositoryPort;
    @InjectMocks
    private FindBooksByCriteriaAdapter findBooksByCriteriaAdapter;
    private BookInformation bookInformation;
    private BookSearchCriteria bookSearchCriteria;
    @BeforeEach
    void setUp() {
        bookSearchCriteria = TestMock.bookSearchCriteria();
        bookInformation = TestMock.bookInformation();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(bookRepositoryPort.findBooksByCriteria(bookSearchCriteria)).thenReturn(new PageImpl<>(List.of(bookInformation)));
        //Act
        Page<BookInformation> result = findBooksByCriteriaAdapter.execute(bookSearchCriteria);
        //Assert
        assertFalse(result.isEmpty());
    }

    @Test
    void executeNotFound() throws MessagingException, IOException {
        //Arrange
        when(bookRepositoryPort.findBooksByCriteria(bookSearchCriteria)).thenReturn(new PageImpl<>(List.of()));
        //Act
        assertThrows(NotFoundException.class, () -> findBooksByCriteriaAdapter.execute(bookSearchCriteria));
    }
}