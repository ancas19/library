package co.com.ancas.uses_cases.auth;

import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.models.model.Attempt;
import co.com.ancas.models.model.AuthLogin;
import co.com.ancas.models.model.AuthToken;
import co.com.ancas.models.model.PeopleFullInfomration;
import co.com.ancas.models.repositories.AttemptRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.jwt.JwtAdapter;
import co.com.ancas.uses_cases.people.ChangeStatusPersonAdapter;
import co.com.ancas.uses_cases.people.FindPeopleFullInformationAdapter;
import co.com.ancas.uses_cases.user.UserDetailsAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginAdapterTest {

    @Mock
    private  UserDetailsAdapter userDetailsAdapter;
    @Mock
    private  BCryptPasswordEncoder passwordEncoder;
    @Mock
    private  JwtAdapter jwtAdapter;
    @Mock
    private  AttemptRepositoryPort attemptRepositoryPort;
    @Mock
    private  UserRepositoryPort userRepositoryPort;
    @Mock
    private  ChangeStatusPersonAdapter changeStatusPersonAdapter;
    @Mock
    private  FindPeopleFullInformationAdapter findPeopleFullInformationAdapter;
    @InjectMocks
    private LoginAdapter loginAdapter;
    private AuthLogin authLogin;
    private User user;
    private PeopleFullInfomration peopleFullInfomration;
    private ArgumentCaptor<Attempt> attemptCaptor;
    private Attempt attempt;
    @BeforeEach
    void setUp() {
        authLogin= TestMock.authLogin();
        user= mock(User.class);
        peopleFullInfomration= TestMock.peopleFullInfomration();
        attemptCaptor=ArgumentCaptor.forClass(Attempt.class);
        attempt=TestMock.attempt();
        when(user.getPassword()).thenReturn("data");
    }

    @Test
    void loginTest() throws MessagingException, IOException {
        //Arrange
        when(userDetailsAdapter.loadUserByUsername(anyString())).thenReturn(user);
        when(userRepositoryPort.findPersonIdByUsername(anyString())).thenReturn(1L);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtAdapter.generateToken(any())).thenReturn("token");
        when(userRepositoryPort.findChangePasswordByUsername(anyString())).thenReturn(true);
        when(findPeopleFullInformationAdapter.execute(any())).thenReturn(peopleFullInfomration);
        //Act
        AuthToken loggedIn=loginAdapter.execute(authLogin);
        //Assert
        assertNotNull(loggedIn);
        assertNotNull(loggedIn.getPeopleFullInfomration());
    }

    @Test
    void loginWrongTest() {
        //Arrange
        when(userDetailsAdapter.loadUserByUsername(anyString())).thenReturn(user);
        when(userRepositoryPort.findPersonIdByUsername(anyString())).thenReturn(1L);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(attemptRepositoryPort.find(anyString())).thenReturn(null);
        doNothing().when(attemptRepositoryPort).save(attemptCaptor.capture());
        //Act
         assertThrows(UnauthorizedException.class,()->loginAdapter.execute(authLogin));
        //Assert
        assertEquals(1, attemptCaptor.getValue().getQuantity());

    }

    @Test
    void loginAttemptsTest() throws MessagingException, IOException {
        //Arrange
        when(userDetailsAdapter.loadUserByUsername(anyString())).thenReturn(user);
        when(userRepositoryPort.findPersonIdByUsername(anyString())).thenReturn(1L);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);
        when(attemptRepositoryPort.find(anyString())).thenReturn(attempt);
        doNothing().when(attemptRepositoryPort).save(attemptCaptor.capture());
        doNothing().when(changeStatusPersonAdapter).execute(any());
        //Act
        assertThrows(UnauthorizedException.class,()->loginAdapter.execute(authLogin));
        //Assert
        assertEquals(4, attemptCaptor.getValue().getQuantity());
    }
}