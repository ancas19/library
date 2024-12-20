package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.AuthorsEntity;
import co.com.ancas.postgres.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorRepositoryAdapter implements AuthorsRepositoryPort {
    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author build) {
        return Mapper.map(this.authorRepository.save(Mapper.map(build, AuthorsEntity.class)), Author.class);
    }
}
