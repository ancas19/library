package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.model.TokenInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.jwt.JwtAdapter;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrentUserAdapterTest {
    @Mock
    private JwtAdapter jwtAdapter;
    @Mock
    private  HttpServletRequest request;
    @InjectMocks
    private CurrentUserAdapter currentUserAdapter;
    private TokenInformation tokenInformation;

    @BeforeEach
    void setUp(){
        tokenInformation = TestMock.tokenInformation();
    }

    @Test
    void execute() {
        // Arrange
        when(request.getHeader(anyString())).thenReturn("Bearer token");
        when(jwtAdapter.getTokenInformation(anyString())).thenReturn(tokenInformation);
        // Act
        CurrentUserInformation respone=currentUserAdapter.execute();
        // Assert
        assertNotNull(respone);
    }

    @Test
    void executeTokenInformationNull() {
        // Arrange
        when(request.getHeader(anyString())).thenReturn("Bearer token");
        when(jwtAdapter.getTokenInformation(anyString())).thenReturn(null);
        // Act and  Assert
        assertThrows(UnauthorizedException.class, () -> currentUserAdapter.execute());
    }
}