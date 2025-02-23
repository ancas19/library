package co.com.ancas.uses_cases.people;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleCreation;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.user.CreateUserAdapter;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePersonAdapterTest {
    @Mock
    private  PeopleRepositoryPort peopleRepositoryPort;
    @Mock
    private  CreateUserAdapter createUserAdapter;
    @InjectMocks
    private CreatePersonAdapter createPersonAdapter;
    private PeopleCreation peopleCreation;
    private People people;

    @BeforeEach
    void setUp() {
        peopleCreation = TestMock.peopleCreation();
        people = TestMock.people();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(this.peopleRepositoryPort.verifyDni(anyString())).thenReturn(false);
        when(this.peopleRepositoryPort.verifyEmail(anyString())).thenReturn(false);
        when(this.peopleRepositoryPort.save(peopleCreation)).thenReturn(people);
        doNothing().when(createUserAdapter).execute(any());
        //Act
        People peopleSaved=createPersonAdapter.execute(peopleCreation);
        //Assert
        assertNotNull(peopleSaved);
    }

    @Test
    void executeDniAlreadyExists() {
        //Arrange
        when(this.peopleRepositoryPort.verifyDni(anyString())).thenReturn(true);
        //Act and Assert
        assertThrows(BadRequestException.class, () -> createPersonAdapter.execute(peopleCreation));
    }

    @Test
    void executeEmailAlreadyExists() {
        //Arrange
        when(this.peopleRepositoryPort.verifyDni(anyString())).thenReturn(false);
        when(this.peopleRepositoryPort.verifyEmail(anyString())).thenReturn(true);
        //Act and Assert
        assertThrows(BadRequestException.class, () -> createPersonAdapter.execute(peopleCreation));
    }
}