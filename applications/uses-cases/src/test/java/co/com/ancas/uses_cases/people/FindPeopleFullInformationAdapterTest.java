package co.com.ancas.uses_cases.people;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.PeopleFullInfomration;
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
class FindPeopleFullInformationAdapterTest {
    @Mock
    private PeopleRepositoryPort peopleRepositoryPort;
    @InjectMocks
    private FindPeopleFullInformationAdapter findPeopleFullInformationAdapter;
    private PeopleFullInfomration peopleFullInfomration;

    @BeforeEach
    void setUp() {
        peopleFullInfomration = TestMock.peopleFullInfomration();
    }

    @Test
    void execute() throws MessagingException {
        //Arrange
        when(peopleRepositoryPort.findPeopleFullInformation(1L)).thenReturn(Optional.ofNullable(peopleFullInfomration));
        //Act
        PeopleFullInfomration peopleFound = findPeopleFullInformationAdapter.execute(1L);
        //Assert
        assertNotNull(peopleFound);
    }

    @Test
    void executeNotFound() {
        //Arrange
        when(peopleRepositoryPort.findPeopleFullInformation(1L)).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findPeopleFullInformationAdapter.execute(1L));
    }
}