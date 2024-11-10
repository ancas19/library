package co.com.ancas.uses_cases.people;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PersonCode;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

import static co.com.ancas.models.enums.Constants.ACTIVE;

@RequiredArgsConstructor
@Component
public class UnblockPeopleAdapter implements IUseCaseVoid<PersonCode> {
    private final PeopleRepositoryPort peopleRepositoryPort;
    @Override
    public void execute(PersonCode personCode) throws MessagingException, IOException {
        //TODO: search code in redis and verify it
        Optional<People> peopleFound = peopleRepositoryPort.findPeopleByEmail(personCode.getEmail());
        if(peopleFound.isEmpty()){
            throw new BadRequestException(Messages.MESSAGES_EMAIL_NOT_FOUND.getMessage());
        }
        peopleFound.get().setStatus(ACTIVE.getConstant());
        this.peopleRepositoryPort.update(peopleFound.get());
    }
}
