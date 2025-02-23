package co.com.ancas.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomAccessDeniedTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @InjectMocks
    private CustomAccessDenied customAccessDenied;

    @Test
    void handle() throws ServletException, IOException {
        //Arrange
        AccessDeniedException exception = new AccessDeniedException("Access Denied");
        //Act
        customAccessDenied.handle(request, response, exception);
        //Assert
        verify(response).setStatus(HttpStatus.FORBIDDEN.value());

    }

}