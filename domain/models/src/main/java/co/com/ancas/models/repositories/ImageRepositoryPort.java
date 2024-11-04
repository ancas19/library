package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Image;

import java.util.Map;

public interface ImageRepositoryPort {
    Image save(Image image);

    Image findById(Long idImage);
}
