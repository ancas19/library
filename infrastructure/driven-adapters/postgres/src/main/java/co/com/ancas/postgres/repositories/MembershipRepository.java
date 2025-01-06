package co.com.ancas.postgres.repositories;

import co.com.ancas.models.model.Membership;
import co.com.ancas.postgres.entities.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {
    @Query(
            """
            SELECT m.id
            FROM MembershipEntity m
            WHERE m.membershipType = :membershipName
            """
    )
    Long findIdMembershipByName(@Param("membershipName") String membershipName);
}
