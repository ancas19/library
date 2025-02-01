package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.Author;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAuthorInformationAdapterTest {
    @Mock
    private FindAuthorByIdAdater findAuthorByIdAdater;
    @Mock
    private AuthorsRepositoryPort authorsRepositoryPort;
    @Mock
    private FindAuthorInformationByIdAdapter findAuthorInformationByIdAdapter;
    @InjectMocks
    private UpdateAuthorInformationAdapter updateAuthorInformationAdapter;
    private Author author;
    private AuthorInformation  authorInformation;

    @BeforeEach
    void setUp() {
        author = TestMock.author();
        authorInformation = TestMock.authorInformation();
    }

    @Test
    void testUpdateAuthorInformation() throws MessagingException, IOException {
        //Arrange
        when(findAuthorByIdAdater.execute(any())).thenReturn(author);
        when(authorsRepositoryPort.existsByNameAndNotId(any(), any())).thenReturn(false);
        when(authorsRepositoryPort.save(any())).thenReturn(author);
        when(findAuthorInformationByIdAdapter.execute(any())).thenReturn(authorInformation);
        //Act
        AuthorInformation authorInformationResult = updateAuthorInformationAdapter.execute(author);
        //Assert
        assertNotNull(authorInformationResult);
    }

    @Test
    void testUpdateAuthorInformationWhenNameExists() {
        //Arrange
        when(findAuthorByIdAdater.execute(any())).thenReturn(author);
        when(authorsRepositoryPort.existsByNameAndNotId(any(), any())).thenReturn(true);
        //Act and Assert
        assertThrows(Exception.class, () -> updateAuthorInformationAdapter.execute(author));
    }
}