package co.com.ancas.uses_cases.books;

import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindBookInformationByIdAdapterTest {
    @Mock
    private BookRepositoryPort bookRepositoryPort;
    @InjectMocks
    private FindBookInformationByIdAdapter findBookInformationByIdAdapter;
    private BookInformation book;

    @BeforeEach
    void setUp() {
        book = TestMock.bookInformation();
    }
    @Test
    void execute() {
        //Arrange
        when(bookRepositoryPort.findBookInformationById(anyLong())).thenReturn(Optional.of(book));
        //Act
        BookInformation bookFound = findBookInformationByIdAdapter.execute(1L);
        //Assert
        assertEquals(book, bookFound);
    }

    @Test
    void executeNotFound() {
        //Arrange
        when(bookRepositoryPort.findBookInformationById(anyLong())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(Exception.class, () -> findBookInformationByIdAdapter.execute(1L));
    }
}