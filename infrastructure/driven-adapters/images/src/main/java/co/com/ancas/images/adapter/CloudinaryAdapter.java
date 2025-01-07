package co.com.ancas.images.adapter;

import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.repositories.CloudinaryPort;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryAdapter implements CloudinaryPort {
    private final Cloudinary cloudinary;
    @Override
    public Map upload(ImageUpload image) throws IOException {
        byte[] decodedBytes = Base64.getDecoder().decode(image.getBase64());
        Map<String, Object> uploadParams = ObjectUtils.asMap(
                "public_id", "library/" + image.getNameFile()
        );
        return cloudinary.uploader().upload(decodedBytes, uploadParams);
    }

    @Override
    public Map delete(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }
}
