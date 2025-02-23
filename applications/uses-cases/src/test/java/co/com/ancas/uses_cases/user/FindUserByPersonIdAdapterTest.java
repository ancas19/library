package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.UserInformation;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserByPersonIdAdapterTest {
    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private FindUserByPersonIdAdapter findUserByPersonIdAdapter;
    private UserInformation userInformation;

    @BeforeEach
    void setUp() {
        userInformation = TestMock.userInformation();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        when(userRepositoryPort.findUserByPersonId(anyLong())).thenReturn(Optional.ofNullable(userInformation));
        // Act
        UserInformation response = findUserByPersonIdAdapter.execute(1L);
        // Assert
        assertNotNull(response);
    }

    @Test
    void executeUserInformationNotFound() {
        // Arrange
        when(userRepositoryPort.findUserByPersonId(anyLong())).thenReturn(Optional.empty());
        // Act and Assert
        assertThrows(NotFoundException.class, () -> findUserByPersonIdAdapter.execute(1L));
    }
}