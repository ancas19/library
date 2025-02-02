package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorCreation;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.Image;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.images.UploadImageAdapter;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateAuthorAdapterTest {
    @Mock
    private  AuthorsRepositoryPort authorsRepositoryPort;
    @Mock
    private  UploadImageAdapter uploadImageAdapter;
    @InjectMocks
    private CreateAuthorAdapter createAuthorAdapter;
    private AuthorCreation authorCreation;
    private Image imageUploaded;
    private Author author;
    @BeforeEach
    void setUp() {
        authorCreation= TestMock.authorCreation();
        imageUploaded= TestMock.image();
        author= TestMock.author();
    }

    @Test
    void testCreateAuthor() throws MessagingException, IOException {
        //Arrange
        when(this.authorsRepositoryPort.existsByName(anyString())).thenReturn(false);
        when(uploadImageAdapter.execute(any())).thenReturn(imageUploaded);
        when(authorsRepositoryPort.save(any())).thenReturn(author);
        //Act
        AuthorInformation authorInformation=createAuthorAdapter.execute(authorCreation);
        //Assert
        assertNotNull(authorInformation);
    }

    @Test
    void testCreateAuthorWhenExistsByName() {
        //Arrange
        when(this.authorsRepositoryPort.existsByName(anyString())).thenReturn(true);
        //Act and ssert
        assertThrows(Exception.class, () -> createAuthorAdapter.execute(authorCreation));
    }
}