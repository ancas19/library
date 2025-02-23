package co.com.ancas.uses_cases.genres;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.repositories.GenresRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllGenresAdapterTest {

    @Mock
    private  GenresRepositoryPort genresRepositoryPort;
    @InjectMocks
    private FindAllGenresAdapter findAllGenresAdapter;

    @Test
    void execute() {
        //Arrange
       when(genresRepositoryPort.findAll()).thenReturn(List.of("genresFound"));
        //Act
        List<String> genresFound = findAllGenresAdapter.execute();
        //Assert
        assertNotNull(genresFound);
        assertFalse(genresFound.isEmpty());
    }

    @Test
    void executeWhenGenresNotFound() {
        //Arrange
        when(genresRepositoryPort.findAll()).thenReturn(List.of());
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findAllGenresAdapter.execute());
    }
}