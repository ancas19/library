package co.com.ancas.security;

import co.com.ancas.service.JwtAppService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private FilterChain filterChain;
    @Mock
    private JwtAppService jwtService;
    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private String token="Bearer adlfkjwlñesfkjlawkñeejflkñsdfj";

    @Test
    void doFilterInternal() throws ServletException, IOException {
        //Arrange
        when(request.getHeader(anyString())).thenReturn(token);
        when(jwtService.validateToken(anyString())).thenReturn(false);
        when(jwtService.extractUsername(anyString())).thenReturn("user");
        when(jwtService.extractRoles(anyString())).thenReturn(List.of("ROLE_USER"));
        doNothing().when(filterChain).doFilter(any(),any());
        //Act
        jwtAuthenticationFilter.doFilterInternal(request,response,filterChain);
        //Assert
        verify(jwtService).validateToken(anyString());
        verify(jwtService).extractUsername(anyString());
        verify(filterChain).doFilter(any(),any());
    }

    @Test
    void doFilterInternalWithNullToken() throws ServletException, IOException {
        //Arrange
        when(request.getHeader(anyString())).thenReturn(null);
        doNothing().when(filterChain).doFilter(any(),any());
        //Act
        jwtAuthenticationFilter.doFilterInternal(request,response,filterChain);
        //Assert
        verify(filterChain).doFilter(any(),any());
    }

    @Test
    void doFilterInternalWithInvalidToken() throws ServletException, IOException {
        //Arrange
        when(request.getHeader(anyString())).thenReturn(token);
        when(jwtService.validateToken(anyString())).thenReturn(true);
        doNothing().when(filterChain).doFilter(any(),any());
        //Act
        jwtAuthenticationFilter.doFilterInternal(request,response,filterChain);
        //Assert
        verify(filterChain).doFilter(any(),any());
    }

}