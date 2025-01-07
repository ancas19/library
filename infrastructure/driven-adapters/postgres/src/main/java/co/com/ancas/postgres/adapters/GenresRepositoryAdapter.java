package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Genre;
import co.com.ancas.models.repositories.GenresRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.GenresEntity;
import co.com.ancas.postgres.repositories.GenresRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenresRepositoryAdapter implements GenresRepositoryPort {
    private final GenresRepository genresRepository;

    @Override
    public List<String> findAll() {
        return this.genresRepository.findAll()
                .stream()
                .map(GenresEntity::getValue)
                .toList();
    }

    @Override
    public Optional<Genre> findByValue(String value) {
        return this.genresRepository.findByValue(value)
                .map(genre-> Mapper.map(genre, Genre.class));
    }
}
