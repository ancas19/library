package co.com.ancas.uses_cases.util;

import co.com.ancas.models.exceptions.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DownloadImageAdapterTest {

    @InjectMocks
    private DownloadImageAdapter downloadImageAdapter;

    @Test
    void downloadImage() {
        //Arrange
        //Act
        String result = downloadImageAdapter.downloadImage("https://cdn3.pixelcut.app/7/20/uncrop_hero_bdf08a8ca6.jpg");
        //Assert
        assertNotNull(result);
    }

    @Test
    void downloadImageWithInvalidUrl() {
        //Arrange
        //Act and Assert
        assertThrows(BadRequestException.class, () -> downloadImageAdapter.downloadImage("invalid-url"));
    }
}