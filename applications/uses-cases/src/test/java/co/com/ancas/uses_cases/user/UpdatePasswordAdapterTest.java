package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.UpdatePassword;
import co.com.ancas.models.model.User;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdatePasswordAdapterTest {
    @Mock
    private  UserRepositoryPort userRepositoryPort;
    @Mock
    private  FindUserByUsernameAdapter findUserByUsernameAdapter;
    @Mock
    private  BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private UpdatePasswordAdapter updatePasswordAdapter;
    private UpdatePassword updatePassword;
    private User user;
    private ArgumentCaptor<User> userArgumentCaptor;
    @BeforeEach
    void setUp(){

        user = TestMock.user();
        updatePassword = TestMock.updatePassword();
        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
    }


    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findUserByUsernameAdapter.execute(updatePassword.getUsername())).thenReturn(user);
        when(passwordEncoder.encode(updatePassword.getPassword())).thenReturn("password");
        doNothing().when(userRepositoryPort).save(userArgumentCaptor.capture());
        //Act
        updatePasswordAdapter.execute(updatePassword);
        //Assert
        assertNotNull(userArgumentCaptor.getValue());

    }

    @Test
    void executePasswordsDoNotMatch() {
        //Arrange
        updatePassword.setConfirmPassword("password2");
        //Act and Assert
        assertThrows(BadRequestException.class, () -> updatePasswordAdapter.execute(updatePassword));
    }
}