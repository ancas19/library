package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.AuthorsSearchCriteria;
import co.com.ancas.models.repositories.AuthorsRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.AuthorsEntity;
import co.com.ancas.postgres.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorRepositoryAdapter implements AuthorsRepositoryPort {
    private final AuthorRepository authorRepository;

    @Override
    public Author save(Author build) {
        return Mapper.map(this.authorRepository.save(Mapper.map(build, AuthorsEntity.class)), Author.class);
    }

    @Override
    public Page<AuthorInformation> findAuthorsByCriteria(AuthorsSearchCriteria authorsSearchCriteria) {
        Pageable pageable = PageRequest.of(authorsSearchCriteria.getPage(), authorsSearchCriteria.getSize());
        return this.authorRepository.findAuthorsByCriteria(authorsSearchCriteria.getSearch().toLowerCase(), pageable);
    }

    @Override
    public Optional<AuthorInformation> findAuthorById(Long aLong) {
        return this.authorRepository.findAuthorById(aLong);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id).map(author -> Mapper.map(author, Author.class));
    }

    @Override
    public boolean existsByNameAndNotId(String fullName, Long id) {
        return this.authorRepository.existsByfullNameAndIdNot(fullName, id);
    }

    @Override
    public boolean existsByName(String fullName) {
        return this.authorRepository.existsByfullName(fullName);
    }
}
