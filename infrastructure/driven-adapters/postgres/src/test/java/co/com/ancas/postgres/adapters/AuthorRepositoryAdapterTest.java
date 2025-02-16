package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.AuthorsSearchCriteria;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.postgres.entities.AuthorsEntity;
import co.com.ancas.postgres.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthorRepositoryAdapterTest {
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private AuthorRepositoryAdapter authorRepositoryAdapter;
    private AuthorsEntity authorEntity;
    private Author author;
    private AuthorInformation authorInformation;
    private AuthorsSearchCriteria authorsSearchCriteria;

    @BeforeEach
    void setUp() {
        authorsSearchCriteria = TestMock.authorsSearchCriteria();
        authorInformation = TestMock.authorInformation();
        author = TestMock.author();
        authorEntity = Mapper.map(author, AuthorsEntity.class);
    }

    @Test
    void save(){
        //Arrange
        when(authorRepository.save(any())).thenReturn(authorEntity);
        //Act
        Author authorCreated=authorRepositoryAdapter.save(author);
        //Assert
        assertNotNull(authorCreated);
    }

    @Test
    void findAuthorsByCriteria(){
        //Arrange
        Page<AuthorInformation> authorInformationPage = new PageImpl<>(List.of(authorInformation));
        when(authorRepository.findAuthorsByCriteria(any(),any())).thenReturn(authorInformationPage);
        //Act
        Page<AuthorInformation> authors=authorRepositoryAdapter.findAuthorsByCriteria(authorsSearchCriteria);
        //Assert
        assertNotNull(authors);
    }


    @Test
    void findAuthorById(){
        //Arrange
        when(authorRepository.findAuthorById(1L)).thenReturn(Optional.of(authorInformation));
        //Act
        Optional<AuthorInformation> authorResponse = authorRepositoryAdapter.findAuthorById(1L);
        //Assert
        assertNotNull(authorResponse);
    }

    @Test
    void findById(){
        //Arrange
        when(authorRepository.findById(1L)).thenReturn(Optional.of(authorEntity));
        //Act
        Optional<Author> authorResponse = authorRepositoryAdapter.findById(1L);
        //Assert
        assertNotNull(authorResponse);
    }

    @Test
    void existsByNameAndNotId(){
        //Arrange
        when(authorRepository.existsByfullNameAndIdNot(any(),any())).thenReturn(true);
        //Act
        boolean exists = authorRepositoryAdapter.existsByNameAndNotId("name", 1L);
        //Assert
        assertTrue(exists);
    }

    @Test
    void existsByName(){
        //Arrange
        when(authorRepository.existsByfullName(any())).thenReturn(true);
        //Act
        boolean exists = authorRepositoryAdapter.existsByName("name");
        //Assert
        assertTrue(exists);
    }

    @Test
    void findByFullName(){
        //Arrange
        when(authorRepository.findByFullName("name")).thenReturn(Optional.of(authorEntity));
        //Act
        Optional<Author> authorResponse = authorRepositoryAdapter.findByFullName("name");
        //Assert
        assertNotNull(authorResponse);
    }

}