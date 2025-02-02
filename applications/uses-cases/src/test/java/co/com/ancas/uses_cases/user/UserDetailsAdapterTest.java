package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDetailsAdapterTest {
    @Mock
    private  UserRepositoryPort userRepositoryPort;
    @Mock
    private  PeopleRepositoryPort peopleRepositoryPort;
    @InjectMocks
    private UserDetailsAdapter userDetailsAdapter;
    private User user;

    @BeforeEach
    void setUp(){
        user = TestMock.user();
    }

    @Test
    void execute() {
        //Arrange
        user.setEmailVerified(false);
        when(userRepositoryPort.findUserByUsername(anyString())).thenReturn(Optional.of(user));
        when(peopleRepositoryPort.verifyPersonBlocked(anyLong())).thenReturn(false);
        when(userRepositoryPort.findRoleByUserId(anyLong())).thenReturn("ROLE_USER");
        //Act
        var userFound = userDetailsAdapter.loadUserByUsername("username");
        //Assert
        assertNotNull(userFound);
    }

    @Test
    void executeUserNotFound() {
        //Arrange
        when(userRepositoryPort.findUserByUsername(anyString())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(UnauthorizedException.class, () -> userDetailsAdapter.loadUserByUsername("username"));
    }

    @Test
    void executeUserBlocked() {
        //Arrange
        when(userRepositoryPort.findUserByUsername(anyString())).thenReturn(Optional.of(user));
        when(peopleRepositoryPort.verifyPersonBlocked(anyLong())).thenReturn(true);
        //Act and Assert
        assertThrows(UnauthorizedException.class, () -> userDetailsAdapter.loadUserByUsername("username"));
    }
}