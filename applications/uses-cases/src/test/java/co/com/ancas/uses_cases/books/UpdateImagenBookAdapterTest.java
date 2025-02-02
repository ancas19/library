package co.com.ancas.uses_cases.books;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.Image;
import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.repositories.BookRepositoryPort;
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
class UpdateImagenBookAdapterTest {

    @Mock
    private  FindBookInformationByIdAdapter findBookInformationByIdAdapter;
    @Mock
    private  BookRepositoryPort bookRepositoryPort;
    @Mock
    private  FindBookByIdAdapter findBookByIdAdapter;
    @Mock
    private  UploadImageAdapter imageAdapter;
    @InjectMocks
    private UpdateImagenBookAdapter updateImagenBookAdapter;
    private Book bookFound;
    private Image image;
    private ImageUpload imageUpload;
    private BookInformation bookInformation;

    @BeforeEach
    void setUp() {
        imageUpload=TestMock.imageUpload();
        image = TestMock.image();
        bookFound = TestMock.book();
        bookInformation = TestMock.bookInformation();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findBookByIdAdapter.execute(anyLong())).thenReturn(bookFound);
        when(imageAdapter.execute(any())).thenReturn(image);
        when(bookRepositoryPort.save(any())).thenReturn(bookFound);
        when(findBookInformationByIdAdapter.execute(anyLong())).thenReturn(bookInformation);
        //Act
        BookInformation result = updateImagenBookAdapter.execute(imageUpload);
        //Assert
        assertNotNull(result);
    }
}