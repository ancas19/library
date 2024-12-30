package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.BooksEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BooksEntity, Long> {
    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);
}
