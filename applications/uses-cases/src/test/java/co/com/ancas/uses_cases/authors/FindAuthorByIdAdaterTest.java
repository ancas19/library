package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
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
class FindAuthorByIdAdaterTest {
    @Mock
    private AuthorsRepositoryPort authorsRepositoryPort;
    @InjectMocks
    private FindAuthorByIdAdater findAuthorByIdAdater;
    private Author author;

    @BeforeEach
    void setUp() {
        author = TestMock.author();
    }

    @Test
    void testFindAuthorById() {
        //Arrange
        when(authorsRepositoryPort.findById(anyLong())).thenReturn(Optional.of(author));
        //Act
        Author authorFound = findAuthorByIdAdater.execute(1L);
        //Assert
        assertNotNull(authorFound);
    }

    @Test
    void testFindAuthorByIdWhenNotFound() {
        //Arrange
        when(authorsRepositoryPort.findById(anyLong())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(Exception.class, () -> findAuthorByIdAdater.execute(1L));
    }
}