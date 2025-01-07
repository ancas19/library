package co.com.ancas.uses_cases.people;

import co.com.ancas.models.model.Image;
import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.model.People;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.uses_cases.images.UploadImageAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UploadProfileImageAdapter implements IUseCaseVoid<ImageUpload> {
    private final FindPeopleByIdAdapter findPeopleByIdAdapter;
    private final PeopleRepositoryPort peopleRepositoryPort;
    private final UploadImageAdapter uploadImageAdapter;
    @Override
    public void execute(ImageUpload imageUpload) throws MessagingException, IOException {
        People people=this.findPeopleByIdAdapter.execute(imageUpload.getId());
        imageUpload.setIdImage(people.getProfileImage());
        Image image=this.uploadImageAdapter.execute(imageUpload);
        people.setProfileImage(image.getId());
        this.peopleRepositoryPort.update(people);
    }
}
