package co.com.ancas.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestAuthenticationFailureHandlerTest {

    @Mock
    private HttpServletRequest request;
    @Mock
    private ServletOutputStream outputStream;
    @Mock
    private HttpServletResponse response;
    @Mock
    private AuthenticationException exception;
    @InjectMocks
    private RestAuthenticationFailureHandler restAuthenticationFailureHandler;

    @Test
    void onAuthenticationFailure() throws IOException, ServletException {
        //Arrange+
        when(response.getOutputStream()).thenReturn( outputStream);
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost:8080"));
        doNothing().when(outputStream).flush();
        //Act
        restAuthenticationFailureHandler.onAuthenticationFailure(request,response,exception);
        //Assert
        verify(outputStream).flush();
    }
}