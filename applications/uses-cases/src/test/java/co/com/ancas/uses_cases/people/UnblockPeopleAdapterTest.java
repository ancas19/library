package co.com.ancas.uses_cases.people;

import co.com.ancas.models.model.Code;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PersonCode;
import co.com.ancas.models.repositories.CodeRepositoryPort;
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
import java.util.Optional;

import static co.com.ancas.models.enums.Constants.ACTIVE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnblockPeopleAdapterTest {
    @Mock
    private  PeopleRepositoryPort peopleRepositoryPort;
    @Mock
    private  CodeRepositoryPort codeRepositoryPort;
    @InjectMocks
    private UnblockPeopleAdapter unblockPeopleAdapter;
    private Code code;
    private People people;
    private PersonCode personCode;
    private ArgumentCaptor<People> peopleArgumentCaptor;

    @BeforeEach
    void setUp() {
        personCode=TestMock.personCode();
        code = TestMock.code();
        people = TestMock.people();
        peopleArgumentCaptor = ArgumentCaptor.forClass(People.class);
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        when(codeRepositoryPort.find(anyString())).thenReturn(code);
        when(peopleRepositoryPort.findPeopleByEmail(anyString())).thenReturn(Optional.of(people));
        doNothing().when(codeRepositoryPort).delete(any());
        when(peopleRepositoryPort.update(peopleArgumentCaptor.capture())).thenReturn(people);
        // Act
        unblockPeopleAdapter.execute(personCode);
        // Assert
        assertNotNull(peopleArgumentCaptor.getValue());
        assertEquals(ACTIVE.getConstant(), peopleArgumentCaptor.getValue().getStatus());
    }

    @Test
    void executeCodeNotFound() {
        // Arrange
        code.setCode("1234");
        when(codeRepositoryPort.find(anyString())).thenReturn(code);
        // Act and Assert
        assertThrows(Exception.class, () -> unblockPeopleAdapter.execute(personCode));
    }

    @Test
    void executePeopleNotFound() {
        // Arrange
        when(codeRepositoryPort.find(anyString())).thenReturn(code);
        when(peopleRepositoryPort.findPeopleByEmail(anyString())).thenReturn(Optional.empty());
        // Act and Assert
        assertThrows(Exception.class, () -> unblockPeopleAdapter.execute(personCode));
    }
}