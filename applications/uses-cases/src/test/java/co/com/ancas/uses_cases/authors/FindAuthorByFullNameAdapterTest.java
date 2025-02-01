package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAuthorByFullNameAdapterTest {

    @Mock
    private AuthorsRepositoryPort authorsRepositoryPort;
    @InjectMocks
    private FindAuthorByFullNameAdapter findAuthorByFullNameAdapter;
    private Author author;

    @BeforeEach
    void setUp() {
        author = new Author();
    }

    @Test
    void testFindAuthorByFullName() throws MessagingException, IOException {
        //Arrange
        when(authorsRepositoryPort.findByFullName(anyString())).thenReturn(Optional.of(author));
        //Act
        Author authorFound = findAuthorByFullNameAdapter.execute("fullName");
        //Assert
        assertNotNull(authorFound);
    }

    @Test
    void testFindAuthorByFullNameWhenNotFound() {
        //Arrange
        when(authorsRepositoryPort.findByFullName(anyString())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(Exception.class, () -> findAuthorByFullNameAdapter.execute("fullName"));
    }
}