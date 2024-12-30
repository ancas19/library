package co.com.ancas.postgres.repositories;

import co.com.ancas.postgres.entities.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    @Query(
            """
            SELECT COUNT(l.id)
            FROM LoanEntity l
            WHERE l.userId = :userId
            AND l.returnDate IS NULL
            """
    )
    Integer countLoansActive(Long userId);
}
