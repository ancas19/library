package co.com.ancas.postgres.repositories;

import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.model.UserInformation;
import co.com.ancas.models.model.UserMembershipInfo;
import co.com.ancas.postgres.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String userName);
    Optional<UserEntity> findUserByUsername(String username);
    @Query(
           """
           SELECT new co.com.ancas.models.model.UserInformation(
               u.id,
               u.username,
               r.roleName,
               m.membershipType,
               u.emailVerified,
               u.changePassword
           )
           FROM UserEntity u
           INNER JOIN RolesEntity r ON r.id = u.roleId
           INNER JOIN MembershipEntity m ON m.id = u.membershipId
           WHERE u.personId = :idPersona
           """
    )

    Optional<UserInformation> findUserByPersonId(@Param("idPersona") Long idPersona);

    @Query(
           """
           SELECT r.roleName
           FROM UserEntity u
           INNER JOIN RolesEntity r ON r.id = u.roleId
           WHERE u.id = :id
           """
    )
    String findRoleByUserId(Long id);

    @Query(
           """
           SELECT u.personId
           FROM UserEntity u
           WHERE u.username = :username
           """
    )
    Long findPersonIdByUsername(String username);

    @Query(
           """
           SELECT new co.com.ancas.models.model.UserMembershipInfo(
                u.id,
                u.username,
                p.email,
                m.membershipType,
                m.loanLimit,
                m.loanPeriodDays,
                m.gracePeriodDays,
                m.finePerDay
           )
           FROM UserEntity u
           INNER JOIN PeopleEntity p ON p.id = u.personId
           INNER JOIN MembershipEntity m ON m.id = u.membershipId
           WHERE p.dni = :dni
           """
    )
    Optional<UserMembershipInfo> findUserAndMembershipInfo(@Param("dni") String s);

    @Query(
            """
            SELECT new co.com.ancas.models.model.UserMembershipInfo(
                 u.id,
                 u.username,
                 p.email,
                 m.membershipType,
                 m.loanLimit,
                 m.loanPeriodDays,
                 m.gracePeriodDays,
                 m.finePerDay
            )
            FROM UserEntity u
            INNER JOIN MembershipEntity m ON m.id = u.membershipId
            WHERE u.id = :UserId
            """
    )
    Optional<UserMembershipInfo> findUserAndMembershipInfoByUserId(@Param("UserId") Long s);

    @Query(
           """
           SELECT new co.com.ancas.models.model.CurrentUserInformation(
                u.username,
                u.id,
                u.personId,
                p.dni,
                r.roleName
           )
           FROM UserEntity u
           INNER JOIN PeopleEntity p ON p.id = u.personId
           INNER JOIN RolesEntity r ON r.id = u.roleId
           WHERE u.username = :username
           """
    )
    CurrentUserInformation findCurrentUserInformation(String username);
}
