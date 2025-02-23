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
class FindUserByUsernameAdapterTest {
    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private FindUserByUsernameAdapter findUserByUsernameAdapter;
    private User user;

    @BeforeEach
    void setUp() {
        user = TestMock.user();
    }

    @Test
    void execute() throws MessagingException, IOException {
       //Arrange
        when(userRepositoryPort.findUserByUsername(anyString())).thenReturn(Optional.of(user));
        //Act
        User userFound = findUserByUsernameAdapter.execute("username");
        //Assert
        assertNotNull(userFound);
    }

    @Test
    void executeUserNotFound() {
        //Arrange
        when(userRepositoryPort.findUserByUsername(anyString())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findUserByUsernameAdapter.execute("username"));
    }
}