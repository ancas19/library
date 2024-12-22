package co.com.ancas.models.repositories;


import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.model.AuthorsSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface AuthorsRepositoryPort {
    Author save(Author build);
    Page<AuthorInformation> findAuthorsByCriteria(AuthorsSearchCriteria authorsSearchCriteria);
    Optional<AuthorInformation> findAuthorById(Long aLong);
}
