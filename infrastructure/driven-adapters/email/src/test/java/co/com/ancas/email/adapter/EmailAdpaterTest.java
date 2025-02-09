package co.com.ancas.email.adapter;

import co.com.ancas.models.model.Email;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmailAdpaterTest {
    @Mock
    private  JavaMailSender javaMailSender;
    @Mock
    private  TemplateEngine templateEngine;
    @Mock
    private MimeMessage mimeMessage;
    @InjectMocks
    private EmailAdpater emailAdpater;
    private ArgumentCaptor<MimeMessage> mimeMessageArgumentCaptor;
    private Email email;

    @BeforeEach
    void setUp() {
        email= TestMock.email();
        mimeMessageArgumentCaptor = ArgumentCaptor.forClass(MimeMessage.class);
    }

    @Test
    void sendEmail() throws MessagingException {
        //Arrange
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(templateEngine.process(anyString(), any())).thenReturn("html");
        doNothing().when(javaMailSender).send(mimeMessageArgumentCaptor.capture());
        //Act
        emailAdpater.sendEmail(email);
        //Assert
        assertNotNull(mimeMessageArgumentCaptor.getValue());
    }

}