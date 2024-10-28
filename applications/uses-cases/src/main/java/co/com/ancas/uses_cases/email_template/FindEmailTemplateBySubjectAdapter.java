package co.com.ancas.uses_cases.email_template;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.repositories.EmailTemplateRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FindEmailTemplateBySubjectAdapter implements IUseCase<String, String> {
    private final EmailTemplateRepositoryPort emailTemplateRepositoryPort;

    @Override
    public String execute(String s) {
        String emailTemplateFound = emailTemplateRepositoryPort.findEmailTemplateBySubject(s);
        if (Objects.isNull(emailTemplateFound)) {
            throw new NotFoundException(Messages.MESSAGES_EMAIL_TEMPLATE_NOT_FOUND.getMessage());
        }
        return emailTemplateFound;
    }
}
