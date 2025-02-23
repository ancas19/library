package co.com.ancas.uses_cases.genres;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.Genre;
import co.com.ancas.models.repositories.GenresRepositoryPort;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindGenreByValueAdapterTest {
    @Mock
    private GenresRepositoryPort genresRepositoryPort;
    @InjectMocks
    private FindGenreByValueAdapter findGenreByValueAdapter;
    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = TestMock.genre();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(genresRepositoryPort.findByValue("value")).thenReturn(Optional.of(genre));
        //Act
        Genre genreFound = findGenreByValueAdapter.execute("value");
        //Assert
        assertNotNull(genreFound);
    }

    @Test
    void executeWhenGenreNotFound() {
        //Arrange
        when(genresRepositoryPort.findByValue("value")).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findGenreByValueAdapter.execute("value"));
    }

}