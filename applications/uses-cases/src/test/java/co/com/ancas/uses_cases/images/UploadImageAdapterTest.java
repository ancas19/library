package co.com.ancas.uses_cases.images;

import co.com.ancas.models.model.Image;
import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.repositories.CloudinaryPort;
import co.com.ancas.models.repositories.ImageRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadImageAdapterTest {
    @Mock
    private  CloudinaryPort cloudinaryPort;
    @Mock
    private  ImageRepositoryPort imageRepositoryPort;
    @InjectMocks
    private UploadImageAdapter uploadImageAdapter;
    private ImageUpload imageUpload;
    private Map map;
    private Image image;
    @BeforeEach
    void setUp() {
        imageUpload = TestMock.imageUpload();
        image = TestMock.image();
        map = new HashMap<>();
        map.put("url", "url");
        map.put("public_id", "public_id");
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        imageUpload.setIdImage(null);
        when(this.cloudinaryPort.upload(any())).thenReturn(map);
        when(this.imageRepositoryPort.save(any())).thenReturn(image);
        //Act
        Image imageSaved= uploadImageAdapter.execute(imageUpload);
        //Assert
        assertNotNull(imageSaved);
    }


    @Test
    void executeWhenImageIdIsNotNull() throws MessagingException, IOException {
        //Arrange
        imageUpload.setIdImage(1l);
        when(this.imageRepositoryPort.findById(anyLong())).thenReturn(image);
        when(this.cloudinaryPort.upload(any())).thenReturn(map);
        when(this.imageRepositoryPort.save(any())).thenReturn(image);
        //Act
        Image imageSaved= uploadImageAdapter.execute(imageUpload);
        //Assert
        assertNotNull(imageSaved);
    }
}