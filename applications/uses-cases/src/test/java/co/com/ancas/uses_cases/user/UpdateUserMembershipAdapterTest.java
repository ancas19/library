package co.com.ancas.uses_cases.user;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.Membership;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.membership.FindIdMembershipByNameAdapter;
import co.com.ancas.uses_cases.membership.FindMembershipByIdAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static co.com.ancas.models.enums.Constants.EMPLOYEE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateUserMembershipAdapterTest {
    @Mock
    private  FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    @Mock
    private  FindIdMembershipByNameAdapter findIdMembershipByNameAdapter;
    @Mock
    private  FindMembershipByIdAdapter findMembershipByIdAdapter;
    @Mock
    private  FindUserFindDniAdapter findUserFindDniAdapter;
    @Mock
    private  EmailRepositoryPort emailRepositoryPort;
    @Mock
    private  UserRepositoryPort userRepositoryPort;
    @InjectMocks
    private UpdateUserMembershipAdapter updateUserMembershipAdapter;
    private User user;
    private Membership membership;
    private ArgumentCaptor<User> userArgumentCaptor;
    private ArgumentCaptor<Email> emailArgumentCaptor;

    @BeforeEach
    void setUp(){
        user = TestMock.user();
        membership = TestMock.membership();
        userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        emailArgumentCaptor = ArgumentCaptor.forClass(Email.class);
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(findUserFindDniAdapter.execute(anyString())).thenReturn(user);
        when(findMembershipByIdAdapter.execute(anyLong())).thenReturn(membership);
        when(this.userRepositoryPort.verifyEmployee(anyLong())).thenReturn(true);
        when(findIdMembershipByNameAdapter.execute(anyString())).thenReturn(1L);
        doNothing().when(userRepositoryPort).save(userArgumentCaptor.capture());
        when(findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("Template");
        when(this.userRepositoryPort.findEmailByUser(anyString())).thenReturn("email");
        doNothing().when(emailRepositoryPort).sendEmail(emailArgumentCaptor.capture());
        //Act
        updateUserMembershipAdapter.execute("User");
        //Assert
        assertNotNull(userArgumentCaptor.getValue());
        assertNotNull(emailArgumentCaptor.getValue());
    }

    @Test
    void executeUserNotFound() throws MessagingException, IOException {
        //Arrange
        membership.setMembershipType(EMPLOYEE.getConstant());
        when(findUserFindDniAdapter.execute(anyString())).thenReturn(user);
        when(findMembershipByIdAdapter.execute(anyLong())).thenReturn(membership);
        //Act and Assert
        assertThrows(BadRequestException.class, () -> updateUserMembershipAdapter.execute("User"));
    }
}