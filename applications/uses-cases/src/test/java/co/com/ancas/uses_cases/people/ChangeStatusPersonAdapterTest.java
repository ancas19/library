package co.com.ancas.uses_cases.people;

import co.com.ancas.models.model.People;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeStatusPersonAdapterTest {
    @Mock
    private  FindPeopleByIdAdapter findPeopleByIdAdapter;
    @Mock
    private  PeopleRepositoryPort peopleRepositoryPort;
    @InjectMocks
    private ChangeStatusPersonAdapter changeStatusPersonAdapter;
    private People people;
    private ArgumentCaptor<People> peopleArgumentCaptor;
    @BeforeEach
    void setUp() {
        people = TestMock.people();
        peopleArgumentCaptor = ArgumentCaptor.forClass(People.class);
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findPeopleByIdAdapter.execute(1L)).thenReturn(people);
        when(peopleRepositoryPort.update(peopleArgumentCaptor.capture())).thenReturn(people);
        //Act
        changeStatusPersonAdapter.execute(1L);
        //Assert
        assertNotNull(peopleArgumentCaptor.getValue());
    }
}