package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    @Query(
           """
           SELECT r.id
           FROM RolesEntity r
           WHERE r.roleName = :roleName
           """
    )
    Long findIdRoleByName(@Param("roleName") String roleName);
}
