package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Image;
import co.com.ancas.models.repositories.ImageRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.ImagesEntity;
import co.com.ancas.postgres.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ImageRepositoryAdapter implements ImageRepositoryPort {
    private final ImageRepository imageRepository;
    @Override
    public Image save(Image image) {
        return Mapper.map(this.imageRepository.save(Mapper.map(image, ImagesEntity.class)),Image.class);
    }

    @Override
    public Image findById(Long idImage) {
        return Mapper.map(this.imageRepository.findById(idImage).orElse(null),Image.class);
    }
}
