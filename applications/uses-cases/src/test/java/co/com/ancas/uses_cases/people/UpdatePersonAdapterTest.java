package co.com.ancas.uses_cases.people;

import co.com.ancas.models.model.People;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePersonAdapterTest {
    @Mock
    private  FindPeopleByIdAdapter findPeopleByIdAdapter;
    @Mock
    private  PeopleRepositoryPort peopleRepositoryPort;
    @InjectMocks
    private UpdatePersonAdapter updatePersonAdapter;
    private People people;

    @BeforeEach
    void setUp() {
        people = TestMock.people();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        when(findPeopleByIdAdapter.execute(anyLong())).thenReturn(people);
        when(peopleRepositoryPort.verifyDniExists(anyString(), anyLong())).thenReturn(false);
        when(peopleRepositoryPort.update(any(People.class))).thenReturn(people);
        // Act
        People result = updatePersonAdapter.execute(people);
        // Assert
        assertNotNull(result);
    }

    @Test
    void executeWithDniExists() throws MessagingException {
        // Arrange
        when(findPeopleByIdAdapter.execute(anyLong())).thenReturn(people);
        when(peopleRepositoryPort.verifyDniExists(anyString(), anyLong())).thenReturn(true);
        // Act and Assert
        assertThrows(Exception.class, () -> updatePersonAdapter.execute(people));
    }
}