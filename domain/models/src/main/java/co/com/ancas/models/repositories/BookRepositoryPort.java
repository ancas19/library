package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.BookSearchCriteria;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BookRepositoryPort {
    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);
    Book save(Book build);
    Page<BookInformation> findBooksByCriteria(BookSearchCriteria bookSearchCriteria);
    Optional<BookInformation> findBookInformationById(Long id);
}
