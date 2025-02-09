package co.com.ancas.postgres.adapters;

import co.com.ancas.postgres.repositories.EmailTemplateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailTemplateRepositoryAdapterTest {
    @Mock
    private EmailTemplateRepository emailTemplateRepository;
    @InjectMocks
    private EmailTemplateRepositoryAdapter emailTemplateRepositoryAdapter;

    @Test
    void findEmailTemplateBySubject() {
        //Arrange
        String subject = "subject";
        String template = "template";
        when(emailTemplateRepository.findBySubject(subject)).thenReturn(template);
        //Act
        String emailTemplate = emailTemplateRepositoryAdapter.findEmailTemplateBySubject(subject);
        //Assert
        assertEquals(template, emailTemplate);
    }
}