package co.com.ancas.exception;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.ForbiddenException;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.response.ErrorResponse;
import co.com.ancas.response.GeneralResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomExceptionHandlerTest {
    @Mock
    private WebRequest request;
    @Mock
    private AuthorizationDeniedException authorizationDeniedException;
    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;
    @Mock
    private BindingResult bindingResult;
    @InjectMocks
    private CustomExceptionHandler customExceptionHandler;

    @Test
    void handelGeneralException() {
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        Exception exception = new Exception("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handelGeneralException(exception,request);
        //Assert
        assertNotNull(result);
        assertEquals(500, result.getStatusCodeValue());
    }

    @Test
    void handleAuthorizationDeniedException(){
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        when(authorizationDeniedException.getMessage()).thenReturn("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handleAuthorizationDeniedException(authorizationDeniedException,request);
        //Assert
        assertNotNull(result);
        assertEquals(403, result.getStatusCodeValue());
    }


    @Test
    void handleInternalAuthenticationServiceException(){
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        InternalAuthenticationServiceException exception = new InternalAuthenticationServiceException("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handleInternalAuthenticationServiceException(exception,request);
        //Assert
        assertNotNull(result);
        assertEquals(401, result.getStatusCodeValue());
    }

    @Test
    void handleMethodNotSupported(){
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handleMethodNotSupported(exception,request);
        //Assert
        assertNotNull(result);
        assertEquals(405, result.getStatusCodeValue());
    }

    @Test
    void handleNoHandlerFoundException(){
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        UnauthorizedException exception = new UnauthorizedException("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handleNoHandlerFoundException(exception,request);
        //Assert
        assertNotNull(result);
        assertEquals(401, result.getStatusCodeValue());
    }

    @Test
    void handleUnauthorizedException(){
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        ForbiddenException exception = new ForbiddenException("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handleForbiddenException(exception,request);
        //Assert
        assertNotNull(result);
        assertEquals(403, result.getStatusCodeValue());
    }

    @Test
    void handleNotFoundException(){
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        NotFoundException exception = new NotFoundException("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handleNotFoundException(exception,request);
        //Assert
        assertNotNull(result);
        assertEquals(404, result.getStatusCodeValue());
    }

    @Test
    void handleBadRequestException(){
        //Arrange
        when(request.getDescription(anyBoolean())).thenReturn("Error");
        BadRequestException exception = new BadRequestException("Error");
        //Act
        ResponseEntity<GeneralResponse<ErrorResponse>> result = customExceptionHandler.handleBadRequestException(exception,request);
        //Assert
        assertNotNull(result);
        assertEquals(400, result.getStatusCodeValue());
    }

    @Test
    void handleMethodArgumentNotValid(){
        //Arrange
        FieldError fieldError = new FieldError("objectName", "fieldName", "Error message");
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));
        //Act
        ResponseEntity<GeneralResponse<Map<String,String>>> result = customExceptionHandler.handleMethodArgumentNotValidException(methodArgumentNotValidException,request);
        //Assert
        assertNotNull(result);
        assertEquals(400, result.getStatusCodeValue());
    }
}