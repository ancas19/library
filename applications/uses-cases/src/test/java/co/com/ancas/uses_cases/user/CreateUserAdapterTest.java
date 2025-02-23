package co.com.ancas.uses_cases.user;

import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserCreation;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.membership.FindIdMembershipByNameAdapter;
import co.com.ancas.uses_cases.password.PasswordGeneratorAdapter;
import co.com.ancas.uses_cases.roles.FindIdRoleByNameAdapter;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserAdapterTest {
    @Mock
    private  FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    @Mock
    private  FindIdMembershipByNameAdapter findIdMembershipByNameAdapter;
    @Mock
    private  PasswordGeneratorAdapter passwordGeneratorAdapter;
    @Mock
    private  FindIdRoleByNameAdapter findIdRoleByNameAdapter;
    @Mock
    private  EmailRepositoryPort emailRepositoryPort;
    @Mock
    private  UserRepositoryPort userRepositoryport;
    @Mock
    private  BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private CreateUserAdapter createUserAdapter;
    private UserCreation userCreation;
    private ArgumentCaptor<User> userArgumentCaptor;
    private ArgumentCaptor<Email> emailArgumentCaptor;

    @BeforeEach
    void setUp() {
        userCreation = TestMock.userCreation();
        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        emailArgumentCaptor = ArgumentCaptor.forClass(Email.class);
    }

    @Test
    void createUserTest() throws MessagingException, IOException {
        //Arrange
        when(userRepositoryport.verifyExistsUserName(anyString())).thenReturn(false);
        when(passwordGeneratorAdapter.execute(anyInt())).thenReturn("password");
        doNothing().when(userRepositoryport).save(userArgumentCaptor.capture());
        when(passwordEncoder.encode(anyString())).thenReturn("password");
        when(findIdRoleByNameAdapter.execute(anyString())).thenReturn(1L);
        when(findIdMembershipByNameAdapter.execute(anyString())).thenReturn(1L);
        when(findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("template");
        doNothing().when(emailRepositoryPort).sendEmail(emailArgumentCaptor.capture());
        //Act
        createUserAdapter.execute(userCreation);
        //Assert
        assertNotNull(userArgumentCaptor.getValue());
        assertNotNull(emailArgumentCaptor.getValue());
    }

}