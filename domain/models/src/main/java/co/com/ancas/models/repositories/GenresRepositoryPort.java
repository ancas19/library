package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenresRepositoryPort {
    List<String> findAll();
    Optional<Genre> findByValue(String genre);
}
