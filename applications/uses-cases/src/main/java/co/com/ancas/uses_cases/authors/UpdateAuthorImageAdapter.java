package co.com.ancas.uses_cases.authors;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.Image;
import co.com.ancas.models.model.ImageUpload;
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
public class UpdateAuthorImageAdapter implements IUseCase<ImageUpload,AuthorInformation> {
    private final UploadImageAdapter uploadImageAdapter;
    private final FindAuthorByIdAdater findAuthorByIdAdater;
    private final AuthorsRepositoryPort authorsRepositoryPort;


    @Override
    @CacheEvict(value = "authors", allEntries = true)
    public AuthorInformation execute(ImageUpload imageUpload) throws MessagingException, IOException {
        Author authorFound=this.findAuthorByIdAdater.execute(imageUpload.getId());
        imageUpload.setIdImage(authorFound.getImageId());
        Image imageuploaded=this.uploadImageAdapter.execute(imageUpload);
        authorFound.setImageId(imageuploaded.getId());
        this.authorsRepositoryPort.save(authorFound);
        return AuthorInformation.builder()
                .id(authorFound.getId())
                .fullName(authorFound.getFullName())
                .nationality(authorFound.getNationality())
                .birthdate(authorFound.getBirthdate())
                .bio(authorFound.getBio())
                .image(imageuploaded.getFilePath())
                .build();
    }
}
