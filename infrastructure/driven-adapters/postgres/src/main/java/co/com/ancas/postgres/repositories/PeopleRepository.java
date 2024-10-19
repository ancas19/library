package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity,Long> {
    boolean existsByDni(String dni);

    boolean existsByEmail(String email);
}
