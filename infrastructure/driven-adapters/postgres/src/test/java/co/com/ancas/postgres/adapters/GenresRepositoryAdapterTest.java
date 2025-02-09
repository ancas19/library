package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Genre;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.postgres.entities.GenresEntity;
import co.com.ancas.postgres.repositories.GenresRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenresRepositoryAdapterTest {
    @Mock
    private GenresRepository genresRepository;
    @InjectMocks
    private GenresRepositoryAdapter genresRepositoryAdapter;
    private GenresEntity genre;

    @BeforeEach
    void setUp() {
        genre = Mapper.map(TestMock.genre(), GenresEntity.class);
    }


    @Test
    void findGenreByName() {
        //Arrange
        String genreName = "genreName";
        when(genresRepository.findByValue(genreName)).thenReturn(Optional.of(genre));
        //Act
        Optional<Genre> genreFound = genresRepositoryAdapter.findByValue(genreName);
        //Assert
        assertNotNull(genreFound);
    }

    @Test
    void findAll() {
        //Arrange
        when(genresRepository.findAll()).thenReturn(List.of(genre));
        //Act
        List<String> genres = genresRepositoryAdapter.findAll();
        //Assert
        assertNotNull(genres);
    }
}