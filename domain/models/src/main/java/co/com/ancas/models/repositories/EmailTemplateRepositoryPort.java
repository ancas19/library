package co.com.ancas.models.repositories;

public interface EmailTemplateRepositoryPort {
    String findEmailTemplateBySubject(String subject);
}
