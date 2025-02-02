package co.com.ancas.uses_cases.people;

import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.model.People;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UploadProfileImageAdapterTest {
    @Mock
    private  FindPeopleByIdAdapter findPeopleByIdAdapter;
    @Mock
    private  PeopleRepositoryPort peopleRepositoryPort;
    @Mock
    private  UploadImageAdapter uploadImageAdapter;
    @InjectMocks
    private UploadProfileImageAdapter uploadProfileImageAdapter;
    private People people;
    private ImageUpload imageUpload;

    @BeforeEach
    void setUp() {
        people = TestMock.people();
        imageUpload = TestMock.imageUpload();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        when(findPeopleByIdAdapter.execute(anyLong())).thenReturn(people);
        when(uploadImageAdapter.execute(any(ImageUpload.class))).thenReturn(TestMock.image());
        when(peopleRepositoryPort.update(any(People.class))).thenReturn(people);
        // Act
        uploadProfileImageAdapter.execute(imageUpload);
        // Assert
        verify(peopleRepositoryPort).update(any(People.class));
        verify(uploadImageAdapter).execute(any(ImageUpload.class));
        verify(findPeopleByIdAdapter).execute(anyLong());
    }

}