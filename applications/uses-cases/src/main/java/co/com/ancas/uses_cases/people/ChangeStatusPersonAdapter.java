package co.com.ancas.uses_cases.people;

import co.com.ancas.models.model.People;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.processing.Find;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static co.com.ancas.models.enums.Constants.INACTIVE;

@Component
@RequiredArgsConstructor
public class ChangeStatusPersonAdapter implements IUseCaseVoid<Long> {
    private final FindPeopleByIdAdapter findPeopleByIdAdapter;
    private final PeopleRepositoryPort peopleRepositoryPort;
    @Override
    public void execute(Long idPerson) throws MessagingException, IOException {
        People peopleFound=findPeopleByIdAdapter.execute(idPerson);
        peopleFound.setStatus(INACTIVE.getConstant());
        peopleRepositoryPort.update(peopleFound);
    }
}
