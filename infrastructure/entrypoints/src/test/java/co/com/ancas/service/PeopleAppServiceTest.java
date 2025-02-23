package co.com.ancas.service;

import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleFullInfomration;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.*;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.response.PeopleFullInfomrationResponse;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.uses_cases.people.*;
import co.com.ancas.uses_cases.user.RecoveryPasswordAdapter;
import co.com.ancas.utils.RequestMocks;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleAppServiceTest {
    @Mock
    private  CreatePersonAdapter createPersonAdapter;
    @Mock
    private  FindPeopleByCriteriaAdapter findPeopleByCriteriaAdapter;
    @Mock
    private  FindPeopleFullInformationAdapter findPeopleByIdAdapter;
    @Mock
    private  UploadProfileImageAdapter uploadProfileImageAdapter;
    @Mock
    private  UpdatePersonAdapter updatePersonAdapter;
    @Mock
    private  ChangeStatusPersonAdapter changeStatusPersonAdapter;
    @Mock
    private  SendCodeToUnblockPersonAdapter sendCodeToUnblockPersonAdapter;
    @Mock
    private  UnblockPeopleAdapter unblockPeopleAdapter;
    @Mock
    private  CurrentUserAppService currentUserAppService;
    @Mock
    private  RecoveryPasswordAdapter recoveryPasswordAdapter;
    @InjectMocks
    private PeopleAppService peopleAppService;
    private People people;
    private PeopleRequest peopleRequest;
    private PersonCodeRequest personCodeRequest;
    private ImageUploadRequest imageUploadRequest;
    private PersonAccessRequest personAccessRequest;
    private PeopleFullInfomration peopleFullInfomration;
    private PasswordRecoveryRequest passwordRecoveryRequest;
    private PeopleInformationRequest peopleInformationRequest;
    private PeopleSearchCriteriaRequest peopleSearchCriteriaRequest;

    @BeforeEach
    void setUp() {
        people = TestMock.people();
        peopleRequest = RequestMocks.peopleRequest();
        personCodeRequest = RequestMocks.personCodeRequest();
        imageUploadRequest = RequestMocks.imageUploadRequest();
        personAccessRequest = RequestMocks.personAccessRequest();
        peopleFullInfomration = TestMock.peopleFullInfomration();
        passwordRecoveryRequest = RequestMocks.passwordRecoveryRequest();
        peopleInformationRequest = RequestMocks.peopleInformationRequest();
        peopleSearchCriteriaRequest = RequestMocks.peopleSearchCriteriaRequest();

    }


    @Test
    void createPeople() throws MessagingException, IOException {
        //Arrange
        when(createPersonAdapter.execute(any())).thenReturn(people);
        //Act
        PeopleResponse result = peopleAppService.createPeople(peopleRequest);
        //Assert
        assertNotNull(result);
    }

    @Test
    void findAllByCriteria() throws MessagingException {
        //Arrange
        Page<People> result = new PageImpl<>(List.of(people));
        when(findPeopleByCriteriaAdapter.execute(any())).thenReturn(result);
        //Act
        PaginationResponse<PeopleResponse> paginationResponse=peopleAppService.findAllByCriteria(peopleSearchCriteriaRequest, 1, 1);
        //Assert
        assertNotNull(paginationResponse);
        assertFalse(paginationResponse.getContent().isEmpty());
    }

    @Test
    void findById() throws MessagingException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentUserPersonIdAndRole(any());
        when(findPeopleByIdAdapter.execute(any())).thenReturn(peopleFullInfomration);
        //Act
        PeopleFullInfomrationResponse result = peopleAppService.findById(1L);
        //Assert
        assertNotNull(result);
    }

    @Test
    void uploadProfileImage() throws MessagingException, IOException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentPersonId(any());
        doNothing().when(uploadProfileImageAdapter).execute(any());
        //Act
        peopleAppService.uploadProfileImage(imageUploadRequest);
        //Assert
        verify(currentUserAppService, times(1)).verifyCurrentPersonId(any());
        verify(uploadProfileImageAdapter, times(1)).execute(any());
    }

    @Test
    void updatePeople() throws MessagingException, IOException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentUserPersonIdAndRole(any());
        when(updatePersonAdapter.execute(any())).thenReturn(people);
        //Act
        PeopleResponse result = peopleAppService.updatePeople(peopleInformationRequest);
        //Assert
        assertNotNull(result);
    }

    @Test
    void blockPeople() throws MessagingException, IOException {
        //Arrange
        doNothing().when(currentUserAppService).verifyCurrentUserPersonIdAndRole(any());
        doNothing().when(changeStatusPersonAdapter).execute(any());
        //Act
        peopleAppService.blockPeople(1L);
        //Assert
        verify(currentUserAppService, times(1)).verifyCurrentUserPersonIdAndRole(any());
        verify(changeStatusPersonAdapter, times(1)).execute(any());
    }

    @Test
    void sendCodeToUnblockPeople() throws MessagingException, IOException {
        //Arrange
        doNothing().when(sendCodeToUnblockPersonAdapter).execute(any());
        //Act
        peopleAppService.sendCodeToUnblockPeople(personAccessRequest);
        //Assert
        verify(sendCodeToUnblockPersonAdapter, times(1)).execute(any());
    }

    @Test
    void unblockPeople() throws MessagingException, IOException {
        //Arrange
        doNothing().when(unblockPeopleAdapter).execute(any());
        //Act
        peopleAppService.unblockPeople(personCodeRequest);
        //Assert
        verify(unblockPeopleAdapter, times(1)).execute(any());
    }

    @Test
    void recoveryPassword() throws MessagingException, IOException {
        //Arrange
        doNothing().when(recoveryPasswordAdapter).execute(any());
        //Act
        peopleAppService.recoveryPassword(passwordRecoveryRequest);
        //Assert
        verify(recoveryPasswordAdapter, times(1)).execute(any());
    }
}