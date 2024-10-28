package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Email;
import jakarta.mail.MessagingException;

public interface EmailRepositoryPort {
    void sendEmail(Email email) throws MessagingException;
}
