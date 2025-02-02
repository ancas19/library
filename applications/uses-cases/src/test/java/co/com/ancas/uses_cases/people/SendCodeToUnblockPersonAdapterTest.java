package co.com.ancas.uses_cases.people;

import co.com.ancas.models.model.Code;
import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PersonAccess;
import co.com.ancas.models.repositories.CodeRepositoryPort;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SendCodeToUnblockPersonAdapterTest {

    @Mock
    private  FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    @Mock
    private  PeopleRepositoryPort peopleRepositoryPort;
    @Mock
    private  EmailRepositoryPort emailRepositoryPort;
    @Mock
    private  CodeRepositoryPort codeRepositoryPort;
    @InjectMocks
    private SendCodeToUnblockPersonAdapter sendCodeToUnblockPersonAdapter;
    private PersonAccess personAccess;
    private People people;
    private ArgumentCaptor<Email> emailArgumentCaptor;
    private ArgumentCaptor<Code> codeArgumentCaptor;

    @BeforeEach
    void setUp() {
        personAccess = TestMock.personAccess();
        people = TestMock.people();
        emailArgumentCaptor = ArgumentCaptor.forClass(Email.class);
        codeArgumentCaptor = ArgumentCaptor.forClass(Code.class);
    }

    @Test
    void execute() throws MessagingException, IOException {
        //Arrange
        when(peopleRepositoryPort.findPeopleByEmail(anyString())).thenReturn(Optional.of(people));
        when( findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("Template");
        doNothing().when(codeRepositoryPort).save(codeArgumentCaptor.capture());
        doNothing().when(emailRepositoryPort).sendEmail(emailArgumentCaptor.capture());
        when(codeRepositoryPort.find(anyString())).thenReturn(null);
        //Act
        sendCodeToUnblockPersonAdapter.execute(personAccess);
        //Assert
        assertNotNull(codeArgumentCaptor.getValue());
        assertNotNull(emailArgumentCaptor.getValue());
    }

    @Test
    void execute1() throws MessagingException, IOException {
        //Arrange
        when(peopleRepositoryPort.findPeopleByEmail(anyString())).thenReturn(Optional.of(people));
        when( findEmailTemplateBySubjectAdapter.execute(anyString())).thenReturn("Template");
        doNothing().when(codeRepositoryPort).save(codeArgumentCaptor.capture());
        doNothing().when(emailRepositoryPort).sendEmail(emailArgumentCaptor.capture());
        when(codeRepositoryPort.find(anyString())).thenReturn(new Code());
        //Act
        sendCodeToUnblockPersonAdapter.execute(personAccess);
        //Assert
        assertNotNull(codeArgumentCaptor.getValue());
        assertNotNull(emailArgumentCaptor.getValue());
    }
}