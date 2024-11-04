package co.com.ancas.uses_cases.people;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.PeopleFullInfomration;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class FindPeopleFullInformationAdapter implements IUseCase<Long, PeopleFullInfomration> {
    private final PeopleRepositoryPort peopleRepositoryPort;
    @Override
    public PeopleFullInfomration execute(Long idPeople) throws MessagingException {
        Optional<PeopleFullInfomration> peopleFound=peopleRepositoryPort.findPeopleById(idPeople);
        if(peopleFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_PEOPLE_NOT_FOUND.getMessage());
        }
        return peopleFound.get();
    }
}
