package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserFindDniAdapterTest {
    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private FindUserFindDniAdapter findUserFindDniAdapter;
    private User user;

    @BeforeEach
    void setUp(){
        user = TestMock.user();
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(userRepositoryPort.findUserFindDni(anyString())).thenReturn(Optional.of(user));
        //Act
        User userFound = findUserFindDniAdapter.execute("123456789");
        //Assert
        assertNotNull(userFound);
    }

    @Test
    void executeUserNotFound() {
        //Arrange
        when(userRepositoryPort.findUserFindDni(anyString())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findUserFindDniAdapter.execute("123456789"));
    }

}
