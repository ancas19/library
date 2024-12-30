package co.com.ancas.models.repositories;

import co.com.ancas.models.model.Book;

public interface BookRepositoryPort {
    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);
    Book save(Book build);
}
