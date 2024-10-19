package co.com.ancas.uses_cases.people;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleCreation;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static co.com.ancas.models.enums.Messages.MESSAGE_ERROR_DNI_ALREADY_EXISTS;
import static co.com.ancas.models.enums.Messages.MESSAGE_ERROR_EMAIL_ALREADY_EXISTS;

@Component
@RequiredArgsConstructor
public class CreatePersonAdapter implements IUseCase<PeopleCreation,People> {
    private final PeopleRepositoryPort peopleRepositoryPort;
    @Override
    public People execute(PeopleCreation people) {
        if(this.peopleRepositoryPort.verifyDni(people.getDni())){
            throw new BadRequestException(MESSAGE_ERROR_DNI_ALREADY_EXISTS.getMessage());
        }
        if(this.peopleRepositoryPort.verifyEmail(people.getEmail())){
            throw new BadRequestException(MESSAGE_ERROR_EMAIL_ALREADY_EXISTS.getMessage());
        }
        People peopleSaved=this.peopleRepositoryPort.save(people);
        return peopleSaved;
    }
}
