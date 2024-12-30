package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.Author;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAuthorByFullNameAdapter  implements IUseCase<String, Author> {
    private final AuthorsRepositoryPort authorsRepositoryPort;
    @Override
    public Author execute(String s) throws MessagingException, IOException {
        Optional<Author> authorFound = authorsRepositoryPort.findByFullName(s);
        if (authorFound.isEmpty()){
            throw new BadRequestException(Messages.MESSAGE_ERROR_AUTHOR_NOT_FOUND_BY_NAME.getMessage().formatted(s));
        }
        return authorFound.get();
    }
}
