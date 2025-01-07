package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.uses_cases.images.UploadImageAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CreateAuthorAdapter implements IUseCase<AuthorCreation, AuthorInformation> {
    private final AuthorsRepositoryPort authorsRepositoryPort;
    private final UploadImageAdapter uploadImageAdapter;
    @Override
    @CacheEvict(value = "authors", allEntries = true)
    public AuthorInformation execute(AuthorCreation authorCreation) throws MessagingException, IOException {
        boolean existsByName=this.authorsRepositoryPort.existsByName(authorCreation.getFullName());
        if(existsByName){
            throw new BadRequestException(Messages.MESSAGE_ERROR_AUTHOR_NAME_ALREADY_EXISTS.getMessage());
        }
        Image imageUploaded=uploadImageAdapter.execute(
                ImageUpload.builder()
                        .base64(authorCreation.getBase64())
                        .nameFile(authorCreation.getNameFile())
                        .build()
        );
        Author authorCreated=this.authorsRepositoryPort.save(
                Author.builder()
                        .fullName(authorCreation.getFullName())
                        .nationality(authorCreation.getNationality())
                        .birthdate(authorCreation.getBirthdate())
                        .bio(authorCreation.getBio())
                        .imageId(imageUploaded.getId())
                        .build()
        );
        return AuthorInformation.builder()
                .id(authorCreated.getId())
                .fullName(authorCreated.getFullName())
                .nationality(authorCreated.getNationality())
                .birthdate(authorCreated.getBirthdate())
                .bio(authorCreated.getBio())
                .image(imageUploaded.getFilePath())
                .build();
    }
}
