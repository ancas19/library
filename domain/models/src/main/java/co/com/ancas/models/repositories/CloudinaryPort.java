package co.com.ancas.models.repositories;

import co.com.ancas.models.model.ImageUpload;

import java.io.IOException;
import java.util.Map;

public interface CloudinaryPort {
    Map<String, Object> upload(ImageUpload image) throws IOException;
    Map<String, Object> delete(String id) throws IOException;
}
