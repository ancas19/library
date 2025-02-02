package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.UserMembershipInfo;
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
class FindUserAndMembershipInfoByUserIdAdapterTest {
    @Mock
    private UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private FindUserAndMembershipInfoByUserIdAdapter findUserAndMembershipInfoByUserIdAdapter;
    private UserMembershipInfo userMembershipInfo;

    @BeforeEach
    void setUp() {
        userMembershipInfo = TestMock.userMembershipInfo();
    }

    @Test
    void execute() throws MessagingException, IOException {
        // Arrange
        when(userRepositoryPort.findUserAndMembershipInfoByUserId(anyLong())).thenReturn(Optional.ofNullable(userMembershipInfo));
        // Act
        UserMembershipInfo response = findUserAndMembershipInfoByUserIdAdapter.execute(1L);
        // Assert
        assertNotNull(response);
    }

    @Test
    void executeUserMembershipInfoNotFound() {
        // Arrange
        when(userRepositoryPort.findUserAndMembershipInfoByUserId(anyLong())).thenReturn(Optional.empty());
        // Act and Assert
        assertThrows(NotFoundException.class, () -> findUserAndMembershipInfoByUserIdAdapter.execute(1L));
    }
}
