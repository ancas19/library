package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Image;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.postgres.entities.ImagesEntity;
import co.com.ancas.postgres.repositories.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ImageRepositoryAdapterTest {
    @Mock
    private ImageRepository imageRepository;
    @InjectMocks
    private ImageRepositoryAdapter imageRepositoryAdapter;
    private ImagesEntity imageEntity;
    private Image image;

    @BeforeEach
    void setUp() {
        image= TestMock.image();
        imageEntity = Mapper.map(image, ImagesEntity.class);
    }


    @Test
    void save(){
        //Arrange
        when(imageRepository.save(any())).thenReturn(imageEntity);
        //Act
        Image imageCreated=imageRepositoryAdapter.save(image);
        //Assert
        assertNotNull(imageCreated);
    }


    @Test
    void findById(){
        //Arrange
        when(imageRepository.findById(any())).thenReturn(Optional.of(imageEntity));
        //Act
        Image imageFound=imageRepositoryAdapter.findById(1L);
        //Assert
        assertNotNull(imageFound);
    }

}