package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Image;


public interface ImageRepositoryPort {
    Image save(Image image);

    Image findById(Long idImage);
}
