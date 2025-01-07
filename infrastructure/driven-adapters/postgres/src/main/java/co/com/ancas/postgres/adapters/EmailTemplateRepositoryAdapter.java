package co.com.ancas.postgres.adapters;

import co.com.ancas.models.repositories.EmailTemplateRepositoryPort;
import co.com.ancas.postgres.repositories.EmailTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailTemplateRepositoryAdapter implements EmailTemplateRepositoryPort {

    private final EmailTemplateRepository emailTemplateRepository;
    @Override
    public String findEmailTemplateBySubject(String subject) {
       return this.emailTemplateRepository.findBySubject(subject);
    }
}
