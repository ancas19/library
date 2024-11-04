package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImagesEntity, Long> {
}
