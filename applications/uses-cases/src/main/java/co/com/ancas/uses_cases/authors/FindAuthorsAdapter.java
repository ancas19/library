package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.AuthorsSearchCriteria;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class FindAuthorsAdapter implements IUseCase<AuthorsSearchCriteria, Page<AuthorInformation>> {
    private final AuthorsRepositoryPort authorsRepositoryPort;

    @Override
    @Cacheable(value = "authors", key = "#peopleSearchCriteria.toString()")
    public Page<AuthorInformation> execute(AuthorsSearchCriteria authorsSearchCriteria) throws MessagingException, IOException {
        Page<AuthorInformation> authorsFound=authorsRepositoryPort.findAuthorsByCriteria(authorsSearchCriteria);
        if(authorsFound.getContent().isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_AUTHOR_NOT_FOUND.getMessage());
        }
        return authorsFound;
    }
}
