package co.com.ancas.uses_cases.email_template;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.repositories.EmailTemplateRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindEmailTemplateBySubjectAdapterTest {

    @Mock
    private EmailTemplateRepositoryPort emailTemplateRepositoryPort;
    @InjectMocks
    private FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;

    @Test
    void execute() {
        //Arrange
        when(emailTemplateRepositoryPort.findEmailTemplateBySubject("subject")).thenReturn("emailTemplate");
        //Act
        String emailTemplateFound = findEmailTemplateBySubjectAdapter.execute("subject");
        //Assert
        assertNotNull(emailTemplateFound);
    }

    @Test
    void executeWhenEmailTemplateNotFound() {
        //Arrange
        when(emailTemplateRepositoryPort.findEmailTemplateBySubject("subject")).thenReturn(null);
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findEmailTemplateBySubjectAdapter.execute("subject"));
    }
}