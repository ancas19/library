package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.Author;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindAuthorByIdAdater implements IUseCase<Long, Author> {
    private final AuthorsRepositoryPort authorsRepositoryPort;

    @Override
    public Author execute(Long id) {
        Optional<Author> authorFound = this.authorsRepositoryPort.findById(id);
        if(authorFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_AUTHOR_NOT_FOUND_BY_ID.getMessage());
        }
        return authorFound.get();
    }
}
