package co.com.ancas.uses_cases.people;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.People;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UpdatePersonAdapter implements IUseCase<People,People> {
    private final FindPeopleByIdAdapter findPeopleByIdAdapter;
    private final PeopleRepositoryPort peopleRepositoryPort;

    @Override
    @CacheEvict(value = "people", allEntries = true)
    public People execute(People people) throws MessagingException, IOException {
        People peopleFound=findPeopleByIdAdapter.execute(people.getId());
        if(this.peopleRepositoryPort.verifyDniExists(people.getDni(),people.getId())){
            throw new BadRequestException(Messages.MESSAGE_ERROR_DNI_ALREADY_EXISTS.getMessage());
        }
        peopleFound.setFirstName(people.getFirstName());
        peopleFound.setLastName(people.getLastName());
        peopleFound.setPhone(people.getPhone());
        peopleFound.setDni(people.getDni());
        return this.peopleRepositoryPort.update(peopleFound);
    }
}
