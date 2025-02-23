package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.models.model.Code;
import co.com.ancas.models.model.PasswordRecovery;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.CodeRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecoveryPasswordAdapterTest {
    @Mock
    private  UserRepositoryPort userRepositoryPort;
    @Mock
    private  BCryptPasswordEncoder passwordEncoder;
    @Mock
    private  CodeRepositoryPort codeRepositoryPort;
    @InjectMocks
    private RecoveryPasswordAdapter recoveryPasswordAdapter;
    private User user;
    private Code code;
    private PasswordRecovery passwordRecovery;
    private ArgumentCaptor<User> userArgumentCaptor;
    @BeforeEach
    void setUp(){
        user = TestMock.user();
        code = TestMock.code();
        passwordRecovery = TestMock.passwordRecovery();
        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        code.setCode(passwordRecovery.getCode());
        when(userRepositoryPort.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(codeRepositoryPort.find(anyString())).thenReturn(code);
        when(passwordEncoder.encode(anyString())).thenReturn("password");
        doNothing().when(userRepositoryPort).save(userArgumentCaptor.capture());
        doNothing().when(codeRepositoryPort).delete(anyString());
        //Act
        recoveryPasswordAdapter.execute(passwordRecovery);
        //Assert
        assertNotNull(userArgumentCaptor.getValue());
        assertEquals("password", userArgumentCaptor.getValue().getPassword());

    }

    @Test
    void executeUserNotFound() {
        //Arrange
        when(userRepositoryPort.findUserByEmail(anyString())).thenReturn(Optional.empty());
        //Act and Assert
        assertThrows(UnauthorizedException.class, () -> recoveryPasswordAdapter.execute(passwordRecovery));
    }

    @Test
    void passwordsNotMarch() {
        //Arrange
        passwordRecovery.setPassword("password2");
        when(userRepositoryPort.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        //Act and Assert
        assertThrows(UnauthorizedException.class, () -> recoveryPasswordAdapter.execute(passwordRecovery));
    }

    @Test
    void executePasswordsNotMatch() {
        //Arrange
        when(userRepositoryPort.findUserByEmail(anyString())).thenReturn(Optional.empty());
        passwordRecovery.setPasswordRepeat("password2");
        //Act and Assert
        assertThrows(UnauthorizedException.class, () -> recoveryPasswordAdapter.execute(passwordRecovery));
    }

    @Test
    void executeCodeNotFound() {
        //Arrange
        code.setCode("code2");
        when(userRepositoryPort.findUserByEmail(anyString())).thenReturn(Optional.of(user));
        when(codeRepositoryPort.find(anyString())).thenReturn(code);
        //Act and Assert
        assertThrows(UnauthorizedException.class, () -> recoveryPasswordAdapter.execute(passwordRecovery));
    }
}