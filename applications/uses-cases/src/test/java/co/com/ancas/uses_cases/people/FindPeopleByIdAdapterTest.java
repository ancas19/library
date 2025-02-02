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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPeopleByIdAdapterTest {
    @Mock
    private PeopleRepositoryPort peopleRepositoryPort;
    @InjectMocks
    private FindPeopleByIdAdapter findPeopleByIdAdapter;
    private People people;

    @BeforeEach
    void setUp() {
        people = TestMock.people();
    }

    @Test
    void execute() throws MessagingException {
        //Arrange
        when(peopleRepositoryPort.findPeopleById(1L)).thenReturn(Optional.ofNullable(people));
        //Act
        People peopleFound = findPeopleByIdAdapter.execute(1L);
        //Assert
        assertNotNull(peopleFound);
    }

    @Test
    void executeNotFound() {
        //Arrange
        when(peopleRepositoryPort.findPeopleById(1L)).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(NullPointerException.class, () -> findPeopleByIdAdapter.execute(1L));
    }

}