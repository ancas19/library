package co.com.ancas.postgres.repositories;

import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.BooksEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BooksEntity, Long> {
    boolean existsByIsbn(String isbn);
    boolean existsByTitle(String title);

    @Query(
            """
            SELECT new co.com.ancas.models.model.BookInformation(
                b.id,
                b.title,
                b.isbn,
                b.publishDate,
                g.value,
                b.availableCopies,
                b.blurb,
                (
                    SELECT i.filePath
                    FROM ImagesEntity i
                    WHERE i.id = b.imageId
                ),
                a.fullName,
                (
                    SELECT i.filePath
                    FROM ImagesEntity i
                    WHERE i.id = a.imageId
                ),
                b.available    
            )
            FROM BooksEntity b
            INNER JOIN AuthorsEntity a ON b.authorId = a.id
            INNER JOIN GenresEntity g ON b.genreId = g.id
            WHERE (lower(b.title) LIKE %:search% OR b.isbn LIKE %:search%)
            AND (a.fullName LIKE %:author%)
            AND (g.value LIKE %:genre%)
            """
    )
    Page<BookInformation> findBooksByCriteria(@Param("search") String lowerCase, @Param("author") String author, @Param("genre") String genre, Pageable pageable);


    @Query(
            """
            SELECT new co.com.ancas.models.model.BookInformation(
                b.id,
                b.title,
                b.isbn,
                b.publishDate,
                g.value,
                b.availableCopies,
                b.blurb,
                (
                    SELECT i.filePath
                    FROM ImagesEntity i
                    WHERE i.id = b.imageId
                ),
                a.fullName,
                (
                    SELECT i.filePath
                    FROM ImagesEntity i
                    WHERE i.id = a.imageId
                ),
                b.available    
            )
            FROM BooksEntity b
            INNER JOIN AuthorsEntity a ON b.authorId = a.id
            INNER JOIN GenresEntity g ON b.genreId = g.id
            WHERE b.id = :id
            """
    )
    Optional<BookInformation> findBookById(@Param("id") Long id);

    boolean existsByIsbnAndIdNot(String isbn, Long id);
    boolean existsByTitleAndIdNot(String isbn, Long id);

    Optional<BooksEntity> findByIsbn(String isbn);
}
