package co.com.ancas.uses_cases.people;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.Code;
import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PersonAccess;
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
public class SendCodeToUnblockPersonAdapter implements IUseCaseVoid<PersonAccess> {
    private final FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    private final PeopleRepositoryPort peopleRepositoryPort;
    private final EmailRepositoryPort emailRepositoryPort;
    private final CodeRepositoryPort codeRepositoryPort;
    @Override
    public void execute(PersonAccess personAccess) throws MessagingException, IOException {
        Optional<People> peopleFound = peopleRepositoryPort.findPeopleByEmail(personAccess.getEmail());
        if(peopleFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGES_EMAIL_NOT_FOUND.getMessage());
        }
        String code=generateCode(personAccess.getEmail());
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
