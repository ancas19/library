package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UpdateAuthorInformationAdapter implements IUseCase<Author, AuthorInformation> {
    private final FindAuthorByIdAdater findAuthorByIdAdater;
    private final AuthorsRepositoryPort authorsRepositoryPort;
    private final FindAuthorInformationByIdAdapter findAuthorInformationByIdAdapter;

    @Override
    public AuthorInformation execute(Author author) throws MessagingException, IOException {
        Author authorFound = this.findAuthorByIdAdater.execute(author.getId());
        boolean existsByName=this.authorsRepositoryPort.existsByNameAndNotId(author.getFullName(),author.getId());
        if(existsByName){
            throw new BadRequestException(Messages.MESSAGE_ERROR_AUTHOR_NAME_ALREADY_EXISTS.getMessage());
        }
        authorFound.setFullName(author.getFullName());
        authorFound.setNationality(author.getNationality());
        authorFound.setBirthdate(author.getBirthdate());
        authorFound.setBio(author.getBio());
        this.authorsRepositoryPort.save(authorFound);
        return this.findAuthorInformationByIdAdapter.execute(authorFound.getId());
    }
}
