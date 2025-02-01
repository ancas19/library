package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.models.utils.TestMock;
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
class FindAuthorInformationByIdAdapterTest {
    @Mock
    private AuthorsRepositoryPort authorsRepositoryPort;
    @InjectMocks
    private FindAuthorInformationByIdAdapter findAuthorInformationByIdAdapter;
    private AuthorInformation authorInformation;

    @BeforeEach
    void setUp() {
        authorInformation = TestMock.authorInformation();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        when(authorsRepositoryPort.findAuthorById(1L)).thenReturn(java.util.Optional.of(authorInformation));
        // Act
        AuthorInformation authorInformationFound = findAuthorInformationByIdAdapter.execute(1L);
        // Assert
        assertEquals(authorInformation, authorInformationFound);
    }

    @Test
    void executeNotFound() {
        // Arrange
        when(authorsRepositoryPort.findAuthorById(1L)).thenReturn(java.util.Optional.empty());
        // Act and Assert
        assertThrows(Exception.class, () -> findAuthorInformationByIdAdapter.execute(1L));
    }

}
