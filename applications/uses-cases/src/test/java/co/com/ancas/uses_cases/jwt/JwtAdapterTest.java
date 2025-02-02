package co.com.ancas.uses_cases.jwt;

import co.com.ancas.models.model.TokenInformation;
import co.com.ancas.models.repositories.JwtRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JwtAdapterTest {
    @Mock
    private  JwtRepositoryPort jwtRepositoryPort;
    @Mock
    private UserDetails userDetails;
    @InjectMocks
    private JwtAdapter jwtAdapter;
    private TokenInformation tokenInformation;
    private ArgumentCaptor<TokenInformation> tokenInformationArgumentCaptor;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(jwtAdapter,"secret","sdlkfjjjlkiojsadfsldkfansdlkfnklsdfjsdfjsdlkfjlsdkfj");
        ReflectionTestUtils.setField(jwtAdapter,"expirationTime",8);
        tokenInformation= TestMock.tokrnInformation();
        tokenInformationArgumentCaptor= ArgumentCaptor.forClass(TokenInformation.class);
    }

    @Test
    void generateToken() {
        //Arrange
        when(userDetails.getUsername()).thenReturn("username");
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        doReturn(authorities).when(userDetails).getAuthorities();
        //Act
        String token = jwtAdapter.generateToken(userDetails);
        //Assert
        assertNotNull(token);
    }

    @Test
    void extractUsername() {
        //Arrange
        when(userDetails.getUsername()).thenReturn("username");
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        doReturn(authorities).when(userDetails).getAuthorities();
        String token = jwtAdapter.generateToken(userDetails);
        //Act
        String username = jwtAdapter.extractUsername(token);
        //Assert
        assertNotNull(username);
    }

    @Test
    void extratcRoles(){
        //Arrange
        when(userDetails.getUsername()).thenReturn("username");
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        doReturn(authorities).when(userDetails).getAuthorities();
        String token = jwtAdapter.generateToken(userDetails);
        //Act
        List<String> roles = jwtAdapter.extractRoles(token);
        //Assert
        assertNotNull(roles);
    }

    @Test
    void saveToken() {
        //Arrange
        doNothing().when(jwtRepositoryPort).save(anyString(),tokenInformationArgumentCaptor.capture());
        //Act
        jwtAdapter.saveToken("token",tokenInformation);
        //Assert
        assertNotNull(tokenInformationArgumentCaptor.getValue());
    }

    @Test
    void deleteToken() {
        //Arrange
        doNothing().when(jwtRepositoryPort).delete(anyString());
        //Act
        jwtAdapter.deleteToken("token");
        //Assert
        verify(jwtRepositoryPort,times(1)).delete("token");
    }

    @Test
    void verifyToken() {
        //Arrange
        when(jwtRepositoryPort.find(anyString())).thenReturn(tokenInformation);
        //Act
        boolean result = jwtAdapter.verifyToken("token");
        //Assert
        assertFalse(result);
    }

    @Test
    void getTokenInformation() {
        //Arrange
        when(jwtRepositoryPort.find(anyString())).thenReturn(tokenInformation);
        //Act
        TokenInformation result = jwtAdapter.getTokenInformation("token");
        //Assert
        assertNotNull(result);
    }
}