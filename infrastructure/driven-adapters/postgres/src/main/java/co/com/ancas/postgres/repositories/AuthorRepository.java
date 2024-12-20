package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.AuthorsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorsEntity,Long> {
}
