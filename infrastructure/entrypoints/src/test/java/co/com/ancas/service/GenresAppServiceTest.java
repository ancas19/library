package co.com.ancas.service;

import co.com.ancas.models.model.Genre;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.genres.FindAllGenresAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenresAppServiceTest {

    @Mock
    private FindAllGenresAdapter findAllGenresAdapter;
    @InjectMocks
    private GenresAppService genresAppService;
    private Genre genre;

    @BeforeEach
    void setUp() {
        genre = TestMock.genre();
    }

    @Test
    void findAll() {
        // Arrange
        when(findAllGenresAdapter.execute()).thenReturn(List.of(genre.getValue()));
        // Act
        List<String> genres = genresAppService.findAll();
        // Assert
        assertFalse(genres.isEmpty());
    }
}