package co.com.ancas.postgres.repositories;

import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.postgres.entities.AuthorsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorsEntity,Long> {
    @Query(
            """
            SELECT new co.com.ancas.models.model.AuthorInformation(
                a.id,
                a.fullName,
                a.nationality,
                a.birthdate,
                a.bio,
                i.filePath
            )
            FROM AuthorsEntity a
            INNER JOIN ImagesEntity i ON a.imageId = i.id
            WHERE LOWER(a.fullName) LIKE %:search%
            """
    )
    Page<AuthorInformation> findAuthorsByCriteria(@Param("search") String search, Pageable pageable);

    @Query(
            """
           SELECT new co.com.ancas.models.model.AuthorInformation(
               a.id,
               a.fullName,
               a.nationality,
               a.birthdate,
               a.bio,
               i.filePath
           )
           FROM AuthorsEntity a
           INNER JOIN ImagesEntity i ON a.imageId = i.id
           WHERE a.id =:idAuthor
            """
    )
    Optional<AuthorInformation> findAuthorById(@Param("idAuthor") Long idAuthor);
}
