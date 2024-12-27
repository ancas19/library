package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAuthorInformationByIdAdapter implements IUseCase<Long, AuthorInformation> {
    private final AuthorsRepositoryPort authorsRepositoryPort;
    @Override
    public AuthorInformation execute(Long aLong) throws MessagingException, IOException {
        Optional<AuthorInformation> authorFound=authorsRepositoryPort.findAuthorById(aLong);
        if(authorFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_AUTHOR_NOT_FOUND.getMessage());
        }
        return authorFound.get();
    }
}
