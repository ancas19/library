package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.AuthorsSearchCriteria;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
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
class FindAuthorsAdapterTest {

    @Mock
    private AuthorsRepositoryPort authorsRepositoryPort;
    @InjectMocks
    private FindAuthorsAdapter findAuthorsAdapter;
    private AuthorsSearchCriteria authorsSearchCriteria;
    private AuthorInformation authorInformation;
    @BeforeEach
    void setUp() {
        authorsSearchCriteria = TestMock.authorsSearchCriteria();
        authorInformation = TestMock.authorInformation();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        Page<AuthorInformation> authorInformationPage=new PageImpl<>(List.of(authorInformation));
        when(authorsRepositoryPort.findAuthorsByCriteria(authorsSearchCriteria)).thenReturn(authorInformationPage);
        // Act
        Page<AuthorInformation> authorInformationFound = findAuthorsAdapter.execute(authorsSearchCriteria);
        // Assert
        assertFalse(authorInformationFound.getContent().isEmpty());
    }

    @Test
    void executeNotFound() {
        // Arrange
        Page<AuthorInformation> authorInformationPage=new PageImpl<>(List.of());
        when(authorsRepositoryPort.findAuthorsByCriteria(authorsSearchCriteria)).thenReturn(authorInformationPage);
        // Act and Assert
        assertThrows(Exception.class, () -> findAuthorsAdapter.execute(authorsSearchCriteria));
    }
}