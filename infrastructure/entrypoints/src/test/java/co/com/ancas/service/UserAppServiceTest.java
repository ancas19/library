package co.com.ancas.service;

import co.com.ancas.models.model.UpdatePassword;
import co.com.ancas.models.model.UserInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.ChangePasswordRequest;
import co.com.ancas.response.UserInformationResponse;
import co.com.ancas.uses_cases.user.FindUserByPersonIdAdapter;
import co.com.ancas.uses_cases.user.UpdatePasswordAdapter;
import co.com.ancas.uses_cases.user.UpdateUserMembershipAdapter;
import co.com.ancas.utils.RequestMocks;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAppServiceTest {
    @Mock
    private  UpdatePasswordAdapter updatePasswordAdapter;
    @Mock
    private  FindUserByPersonIdAdapter findUserByPersonIdAdapter;
    @Mock
    private  CurrentUserAppService currentUserAppService;
    @Mock
    private  UpdateUserMembershipAdapter updateUserMembershipAdapter;
    @InjectMocks
    private UserAppService userAppService;
    private UserInformation userInformation;
    private ChangePasswordRequest changePasswordRequest;
    private ArgumentCaptor<UpdatePassword> updatePasswordCaptor;

    @BeforeEach
    void setUp() {
        updatePasswordCaptor = ArgumentCaptor.forClass(UpdatePassword.class);
        userInformation = TestMock.userInformation();
        changePasswordRequest = RequestMocks.changePasswordRequest();
    }

    @Test
    void updatePassword() throws MessagingException, IOException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentUsername(anyString());
        doNothing().when(updatePasswordAdapter).execute(updatePasswordCaptor.capture());
        //Act
        userAppService.updatePassword(changePasswordRequest);
        //Assert
        assertNotNull(updatePasswordCaptor.getValue());
    }

    @Test
    void findUserByPersonid() throws MessagingException, IOException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentUserPersonIdAndRole(1L);
        when(findUserByPersonIdAdapter.execute(1L)).thenReturn(userInformation);
        //Act
        UserInformationResponse result = userAppService.findUserByPersonid(1L);
        //Assert
        assertNotNull(result);
    }

    @Test
    void updateUserMembership() throws MessagingException, IOException {
        //Arrange
        doNothing().when(updateUserMembershipAdapter).execute("username");
        //Act
        userAppService.updateUserMembership("username");
        //Assert
        verify(updateUserMembershipAdapter, times(1)).execute("username");
    }
}