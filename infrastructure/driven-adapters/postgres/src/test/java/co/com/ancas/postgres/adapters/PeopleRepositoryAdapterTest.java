package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleCreation;
import co.com.ancas.models.model.PeopleFullInfomration;
import co.com.ancas.models.model.PeopleSearchCriteria;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.postgres.entities.PeopleEntity;
import co.com.ancas.postgres.repositories.PeopleRepository;
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
class PeopleRepositoryAdapterTest {
    @Mock
    private PeopleRepository peopleRepository;
    @InjectMocks
    private PeopleRepositoryAdapter peopleRepositoryAdapter;
    private People people;
    private PeopleEntity peopleEntity;
    private PeopleCreation peopleCreation;
    private PeopleSearchCriteria peopleSearchCriteria;
    private PeopleFullInfomration peopleFullInfomration;

    @BeforeEach
    void setUp() {
        peopleCreation = TestMock.peopleCreation();
        people = TestMock.people();
        peopleEntity = Mapper.map(people, PeopleEntity.class);
        peopleSearchCriteria = TestMock.peopleSearchCriteria();
        peopleFullInfomration = TestMock.peopleFullInfomration();
    }

    @Test
    void save(){
        //Arrange
        when(peopleRepository.save(any())).thenReturn(peopleEntity);
        //Act
        People peopleCreated=peopleRepositoryAdapter.save(peopleCreation);
        //Assert
        assertNotNull(peopleCreated);
    }

    @Test
    void verifyEmail(){
        //Arrange
        when(peopleRepository.existsByEmail(any())).thenReturn(true);
        //Act
        boolean result=peopleRepositoryAdapter.verifyEmail("email");
        //Assert
        assertTrue(result);
    }

    @Test
    void verifyDni(){
        //Arrange
        when(peopleRepository.existsByDni(any())).thenReturn(true);
        //Act
        boolean result=peopleRepositoryAdapter.verifyDni("dni");
        //Assert
        assertTrue(result);
    }

    @Test
    void findPeopleByCriteria(){
        //Arrange
        Page<PeopleEntity> pagePeople= new PageImpl<>(List.of(peopleEntity));
        when(peopleRepository.findPeopleByCriteria(any(),any(),any())).thenReturn(pagePeople);
        //Act
        Page<People> peopleFound=peopleRepositoryAdapter.findPeopleByCriteria(peopleSearchCriteria);
        //Assert
        assertNotNull(peopleFound);
        assertFalse(peopleFound.getContent().isEmpty());
    }

    @Test
    void findPeopleFullInformation(){
        //Arrange
        when(peopleRepository.findPeopleFullInformation(any())).thenReturn(Optional.of(peopleFullInfomration));
        //Act
        Optional<PeopleFullInfomration> peopleFullInfomrationFound=peopleRepositoryAdapter.findPeopleFullInformation(1L);
        //Assert
        assertTrue(peopleFullInfomrationFound.isPresent());
    }

    @Test
    void findPeopleById(){
        //Arrange
        when(peopleRepository.findByIdAndStatus(any(),any())).thenReturn(Optional.of(peopleEntity));
        //Act
        Optional<People> peopleFound=peopleRepositoryAdapter.findPeopleById(1L);
        //Assert
        assertTrue(peopleFound.isPresent());
    }

    @Test
    void update(){
        //Arrange
        when(peopleRepository.save(any())).thenReturn(peopleEntity);
        //Act
        People peopleUpdated=peopleRepositoryAdapter.update(people);
        //Assert
        assertNotNull(peopleUpdated);
    }

    @Test
    void verifyDniExists(){
        //Arrange
        when(peopleRepository.existsByDniAndNotId(any(),any())).thenReturn(true);
        //Act
        boolean result=peopleRepositoryAdapter.verifyDniExists("dni",1L);
        //Assert
        assertTrue(result);
    }

    @Test
    void verifyPersonBlocked(){
        //Arrange
        when(peopleRepository.existsByIdAndStatus(any(),any())).thenReturn(true);
        //Act
        boolean result=peopleRepositoryAdapter.verifyPersonBlocked(1L);
        //Assert
        assertTrue(result);
    }

    @Test
    void findPeopleByEmail(){
        //Arrange
        when(peopleRepository.findByEmail(any())).thenReturn(Optional.of(peopleEntity));
        //Act
        Optional<People> peopleFound=peopleRepositoryAdapter.findPeopleByEmail("email");
        //Assert
        assertTrue(peopleFound.isPresent());
    }
}