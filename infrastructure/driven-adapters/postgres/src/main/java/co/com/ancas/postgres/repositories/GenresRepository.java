package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.GenresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenresRepository extends JpaRepository<GenresEntity,Long> {
    Optional<GenresEntity> findByValue(String genre);
}
