package co.com.ancas.uses_cases.people;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.Code;
import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PersonAcces;
import co.com.ancas.models.repositories.CodeRepositoryPort;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import co.com.ancas.uses_cases.util.RandomCode;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static co.com.ancas.models.enums.Constants.*;

@RequiredArgsConstructor
@Component
public class SendCodeToUnblockPersonAdapter implements IUseCaseVoid<PersonAcces> {
    private final PeopleRepositoryPort peopleRepositoryPort;
    private final FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    private final EmailRepositoryPort emailRepositoryPort;
    private final CodeRepositoryPort codeRepositoryPort;
    @Override
    public void execute(PersonAcces personAcces) throws MessagingException, IOException {
        Optional<People> peopleFound = peopleRepositoryPort.findPeopleByEmail(personAcces.getEmail());
        if(peopleFound.isEmpty()){
            throw new BadRequestException(Messages.MESSAGES_EMAIL_NOT_FOUND.getMessage());
        }
        String code=generateCode(personAcces.getEmail());
        String emailTemplate = findEmailTemplateBySubjectAdapter.execute(UNBLOCK_USER.getConstant());
        emailTemplate = emailTemplate.replace(":verification_code", code);
        emailTemplate = emailTemplate.replace(":name", peopleFound.get().getFirstName());
        this.emailRepositoryPort.sendEmail(
                Email.builder()
                        .recipient(List.of(peopleFound.get().getEmail()))
                        .subject(CODE_UNBLOCK_PERSON.getConstant())
                        .body(emailTemplate)
                        .build()
        );
        codeRepositoryPort.save(co.com.ancas.models.model.Code.builder()
                .code(code)
                .email(peopleFound.get().getEmail())
                .build());
    }

    private String generateCode(String email) {
        Code codeFound=codeRepositoryPort.find(email);
        if(Objects.nonNull(codeFound)){
            return codeFound.getCode();
        }
        return RandomCode.generateRandomCode();
    }


}
