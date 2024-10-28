package co.com.ancas.email.adapter;

import co.com.ancas.models.model.Email;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
@Slf4j
public class EmialAdpater implements EmailRepositoryPort {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendEmail(Email email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email.getRecipient());
        helper.setSubject(email.getSubject());

        Context context = new Context();
        context.setVariable("body", email.getBody());
        String html = templateEngine.process("email", context);
        helper.setText(html, true);
        javaMailSender.send(message);
        log.info("Email sent successfully to: {}", email);
    }
}
