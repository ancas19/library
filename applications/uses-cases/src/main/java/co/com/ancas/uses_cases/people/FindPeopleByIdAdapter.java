package co.com.ancas.uses_cases.people;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.People;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindPeopleByIdAdapter implements IUseCase<Long, People> {
    private final PeopleRepositoryPort peopleRepositoryPort;

    @Override
    public People execute(Long aLong) throws MessagingException {
        Optional<People> peopleFound=peopleRepositoryPort.findPeopleById(aLong);
        if(peopleFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_PEOPLE_NOT_FOUND.getMessage());
        }
        return peopleFound.get();
    }
}
