package co.com.ancas.uses_cases.books;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.Image;
import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.uses_cases.images.UploadImageAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UpdateImagenBookAdapter implements IUseCase<ImageUpload, BookInformation> {
    private final FindBookInformationByIdAdapter findBookInformationByIdAdapter;
    private final BookRepositoryPort bookRepositoryPort;
    private final FindBookByIdAdapter findBookByIdAdapter;
    private final UploadImageAdapter imageAdapter;

    @Override
    public BookInformation execute(ImageUpload imageUpload) throws MessagingException, IOException {
        Book bookFound = findBookByIdAdapter.execute(imageUpload.getId());
        imageUpload.setIdImage(bookFound.getImageId());
        Image imageUploaded = this.imageAdapter.execute(imageUpload);
        bookFound.setImageId(imageUploaded.getId());
        this.bookRepositoryPort.save(bookFound);
        return this.findBookInformationByIdAdapter.execute(bookFound.getId());
    }
}
