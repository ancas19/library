package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserInformation;
import co.com.ancas.models.model.UserMembershipInfo;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.postgres.entities.UserEntity;
import co.com.ancas.postgres.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryAdapterTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter;
    private User user;
    private UserEntity userEntity;
    private UserInformation userInformation;
    private UserMembershipInfo userMembershipInfo;
    private CurrentUserInformation currentUserInformation;


    @BeforeEach
    void setUp() {
        user = TestMock.user();
        userInformation = TestMock.userInformation();
        userEntity = Mapper.map(user, UserEntity.class);
        userMembershipInfo = TestMock.userMembershipInfo();
        currentUserInformation = TestMock.currentUserInformation();
    }

    @Test
    void save(){
        //Arrange
        when(userRepository.save(any())).thenReturn(userEntity);
        //Act
        userRepositoryAdapter.save(user);
        //Assert
        verify(userRepository, times(1)).save(any());
    }

    @Test
    void findUserByUsername(){
        //Arrange
        when(userRepository.findUserByUsername(anyString())).thenReturn(java.util.Optional.of(userEntity));
        //Act
        Optional<User> userFound=userRepositoryAdapter.findUserByUsername("username");
        //Assert
        assertTrue(userFound.isPresent());
    }

    @Test
    void findUserByPersonId(){
        //Arrange
        when(userRepository.findUserByPersonId(anyLong())).thenReturn(Optional.of(userInformation));
        //Act
        Optional<UserInformation> userFound=userRepositoryAdapter.findUserByPersonId(1L);
        //Assert
        assertTrue(userFound.isPresent());
    }

    @Test
    void findRoleByUserId(){
        //Arrange
        when(userRepository.findRoleByUserId(anyLong())).thenReturn("role");
        //Act
        String role=userRepositoryAdapter.findRoleByUserId(1L);
        //Assert
        assertNotNull(role);
    }

    @Test
    void findPersonIdByUsername(){
        //Arrange
        when(userRepository.findPersonIdByUsername(anyString())).thenReturn(1L);
        //Act
        Long personId=userRepositoryAdapter.findPersonIdByUsername("username");
        //Assert
        assertNotNull(personId);
    }

    @Test
    void findUserAndMembershipInfo(){
        //Arrange
        when(userRepository.findUserAndMembershipInfo(anyString())).thenReturn(Optional.of(userMembershipInfo));
        //Act
        Optional<UserMembershipInfo> userMembershipResponse=userRepositoryAdapter.findUserAndMembershipInfo("username");
        //Assert
        assertTrue(userMembershipResponse.isPresent());
    }


    @Test
    void findCurrentUserInformation(){
        //Arrange
        when(userRepository.findCurrentUserInformation(anyString())).thenReturn(currentUserInformation);
        //Act
        CurrentUserInformation currentUserInformationResponse=userRepositoryAdapter.findCurrentUserInformation("username");
        //Assert
        assertNotNull(currentUserInformationResponse);
    }

    @Test
    void findUserAndMembershipInfoByUserId(){
        //Arrange
        when(userRepository.findUserAndMembershipInfoByUserId(anyLong())).thenReturn(Optional.of(userMembershipInfo));
        //Act
        Optional<UserMembershipInfo> userMembershipResponse=userRepositoryAdapter.findUserAndMembershipInfoByUserId(1L);
        //Assert
        assertTrue(userMembershipResponse.isPresent());
    }

    @Test
    void findUserByEmail(){
        //Arrange
        when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(userEntity));
        //Act
        Optional<User> userFound=userRepositoryAdapter.findUserByEmail("email");
        //Assert
        assertTrue(userFound.isPresent());
    }

    @Test
    void findEmailsAdmins(){
        //Arrange
        when(userRepository.findEmailsAdmins()).thenReturn(List.of("email"));
        //Act
        List<String> emails=userRepositoryAdapter.findEmailsAdmins();
        //Assert
        assertNotNull(emails);
    }

    @Test
    void findUserFindDni(){
        //Arrange
        when(userRepository.findUserFindDni(anyString())).thenReturn(Optional.of(userEntity));
        //Act
        Optional<User> userFound=userRepositoryAdapter.findUserFindDni("dni");
        //Assert
        assertTrue(userFound.isPresent());
    }

    @Test
    void findChangePasswordByUsername(){
        //Arrange
        when(userRepository.findChangePasswordByUsername(anyString())).thenReturn(true);
        //Act
        boolean changePassword=userRepositoryAdapter.findChangePasswordByUsername("username");
        //Assert
        assertTrue(changePassword);
    }

    @Test
    void findEmailByUser(){
        //Arrange
        when(userRepository.findEmailByUser(anyString())).thenReturn("email@email.com");
        //Act
        String emailFound=userRepositoryAdapter.findEmailByUser("username");
        //Assert
        assertNotNull(emailFound);
    }

    @Test
    void verifyEmployee(){
        //Arrange
        when(userRepository.verifyEmployee(anyLong())).thenReturn(1);
        //Act
        boolean isEmployee=userRepositoryAdapter.verifyEmployee(1L);
        //Assert
        assertTrue(isEmployee);
    }

    @Test
    void verifyExistsUserName(){
        //Arrange
        when(userRepository.existsByUsername(anyString())).thenReturn(true);
        //Act
         boolean userFound=userRepositoryAdapter.verifyExistsUserName("username");
        //Assert
        assertTrue(userFound);
    }
}