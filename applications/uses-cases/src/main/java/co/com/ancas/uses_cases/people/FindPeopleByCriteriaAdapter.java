package co.com.ancas.uses_cases.people;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleSearchCriteria;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class FindPeopleAdapter implements IUseCase<PeopleSearchCriteria, Page<People>> {
    private final PeopleRepositoryPort peopleRepositoryPort;
    @Override
    public Page<People> execute(PeopleSearchCriteria peopleSearchCriteria) throws MessagingException {
        Page<People> peopleFound=peopleRepositoryPort.findPeopleByCriteria(peopleSearchCriteria);
        if(peopleFound.getContent().isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_PEOPLE_NOT_FOUND.getMessage());
        }
        return peopleFound;
    }
}
