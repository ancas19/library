package co.com.ancas.postgres.repositories;

import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleFullInfomration;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.PeopleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Query(
            """
            SELECT new co.com.ancas.models.model.PeopleFullInfomration(
                p.id,
                p.dni,
                p.firstName,
                p.lastName,
                p.email,
                p.phone,
                r.roleName,
                m.membershipType,
                (
                    SELECT i.filePath
                    FROM ImagesEntity i
                    WHERE i.id=p.profileImage
                ),
                u.username,
                p.status
            )
            FROM PeopleEntity p
            INNER JOIN UserEntity u ON p.id = u.personId
            INNER JOIN RolesEntity r ON r.id = u.roleId
            INNER JOIN MembershipEntity m ON m.id = u.membershipId
            WHERE p.id = :idPeople
            """
    )
    Optional<PeopleFullInfomration> findPeopleFullInformation(Long idPeople);
    @Query("SELECT COUNT(p) > 0 FROM PeopleEntity p WHERE p.dni = :dni AND p.id <> :id")
    boolean existsByDniAndNotId(@Param("dni") String dni, @Param("id") Long id);

    Optional<PeopleEntity> findByIdAndStatus(Long aLong, String constant);

    boolean existsByIdAndStatus(Long personId, String constant);

    Optional<PeopleEntity> findByEmailAndStatus(String email, String constant);
}
