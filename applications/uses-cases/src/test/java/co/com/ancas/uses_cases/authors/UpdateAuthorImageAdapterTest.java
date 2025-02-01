package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.Image;
import co.com.ancas.models.model.ImageUpload;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateAuthorImageAdapterTest {
    @Mock
    private  UploadImageAdapter uploadImageAdapter;
    @Mock
    private  FindAuthorByIdAdater findAuthorByIdAdater;
    @Mock
    private  AuthorsRepositoryPort authorsRepositoryPort;
    @InjectMocks
    private UpdateAuthorImageAdapter updateAuthorImageAdapter;
    private ImageUpload imageUpload;
    private Author author;
    private Image image;

    @BeforeEach
    void setUp() {
        imageUpload = TestMock.imageUpload();
        author = TestMock.author();
        image = TestMock.image();
    }

    @Test
    void testUpdateAuthorImage() throws MessagingException, IOException {
        //Arrange
        when(findAuthorByIdAdater.execute(anyLong())).thenReturn(author);
        when(uploadImageAdapter.execute(any())).thenReturn(image);
        when(authorsRepositoryPort.save(any())).thenReturn(author);
        //Act
        AuthorInformation authorInformation = updateAuthorImageAdapter.execute(imageUpload);
        //Assert
        assertNotNull(authorInformation);
    }
}