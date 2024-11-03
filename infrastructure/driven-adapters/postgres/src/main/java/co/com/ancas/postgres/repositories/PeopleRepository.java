package co.com.ancas.postgres.repositories;

import co.com.ancas.models.model.People;
import co.com.ancas.postgres.entities.PeopleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<PeopleEntity,Long> {
    boolean existsByDni(String dni);

    boolean existsByEmail(String email);

    @Query(
            """
            SELECT p
            FROM PeopleEntity p
            INNER JOIN UserEntity u ON p.id = u.personId
            INNER JOIN RolesEntity r ON r.id = u.roleId
            WHERE (Lower(CONCAT(p.firstName, ' ', p.lastName)) LIKE :search 
            OR p.dni LIKE :search OR LOWER(u.username) LIKE :search)
            and r.roleName = :role
            ORDER BY p.id
            """
    )
    Page<PeopleEntity> findPeopleByCriteria(@Param("search") String search,@Param("role") String role, Pageable pageable);
}
