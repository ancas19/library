package co.com.ancas.uses_cases.images;

import co.com.ancas.models.model.Image;
import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.repositories.CloudinaryPort;
import co.com.ancas.models.repositories.ImageRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UploadImageAdapter implements IUseCase<ImageUpload, Image> {
    private final CloudinaryPort cloudinaryPort;
    private final ImageRepositoryPort imageRepositoryPort;
    @Override
    public Image execute(ImageUpload imageUpload) throws MessagingException, IOException {
        if(Objects.isNull(imageUpload.getIdImage())){
            Map cloudinaryResponse = this.cloudinaryPort.upload(imageUpload);
            return this.imageRepositoryPort.save(
                    Image.builder()
                            .fileName(imageUpload.getNameFile())
                            .filePath(cloudinaryResponse.get("url").toString())
                            .idServer(cloudinaryResponse.get("public_id").toString())
                            .build()
            );
        }
        Image imageFound = this.imageRepositoryPort.findById(imageUpload.getIdImage());
        cloudinaryPort.delete(imageFound.getIdServer());
        Map cloudinaryResponseUpdate = this.cloudinaryPort.upload(imageUpload);
        imageFound.setFilePath(cloudinaryResponseUpdate.get("url").toString());
        imageFound.setIdServer(cloudinaryResponseUpdate.get("public_id").toString());
        imageFound.setFileName(imageUpload.getNameFile());
        return this.imageRepositoryPort.save(imageFound);
    }
}
