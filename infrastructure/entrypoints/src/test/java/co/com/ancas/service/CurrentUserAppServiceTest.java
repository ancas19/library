package co.com.ancas.service;

import co.com.ancas.models.exceptions.ForbiddenException;
import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.user.CurrentUserAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrentUserAppServiceTest {
    @Mock
    private CurrentUserAdapter currentUserAdapter;
    @InjectMocks
    private CurrentUserAppService currentUserAppService;
    private CurrentUserInformation currentUserInformation;

    @BeforeEach
    void setUp() {
        currentUserInformation = TestMock.currentUserInformation();
    }

    @Test
    void verifyCurrentUserDniAndRole(){
        // Arrange
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act
       currentUserAppService.verifyCurrentUserDniAndRole("12345678");
        // Assert
        verify(currentUserAdapter).execute();
    }

    @Test
    void verifyCurrentUserDniAndRoleException(){
        // Arrange
        currentUserInformation.setRole("USER");
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act and Assert
        assertThrows(ForbiddenException.class,()->currentUserAppService.verifyCurrentUserDniAndRole("123456"));
    }

    @Test
    void verifyUsername(){
        // Arrange
        currentUserInformation.setUsername("username");
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act
        currentUserAppService.verifyCurrentUsername("username");
        // Assert
        verify(currentUserAdapter).execute();
    }

    @Test
    void verifyUsernameException(){
        // Arrange
        currentUserInformation.setUsername("username");
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act and Assert
        assertThrows(ForbiddenException.class,()->currentUserAppService.verifyCurrentUsername("username2"));
    }

    @Test
    void verifyCurrentUserPersonIdAndRole(){
        // Arrange
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act
        currentUserAppService.verifyCurrentUserPersonIdAndRole(1L);
        // Assert
        verify(currentUserAdapter).execute();
    }

    @Test
    void verifyCurrentUserPersonIdAndRoleException(){
        // Arrange
        currentUserInformation.setRole("USER");
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act and Assert
        assertThrows(ForbiddenException.class,()->currentUserAppService.verifyCurrentUserPersonIdAndRole(2L));
    }

    @Test
    void verifyCurrentPersonId(){
        // Arrange
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act
        currentUserAppService.verifyCurrentPersonId(1L);
        // Assert
        verify(currentUserAdapter).execute();
    }

    @Test
    void verifyCurrentPersonIdException(){
        // Arrange
        when(currentUserAdapter.execute()).thenReturn(currentUserInformation);
        // Act and Assert
        assertThrows(ForbiddenException.class,()->currentUserAppService.verifyCurrentPersonId(2L));
    }
}