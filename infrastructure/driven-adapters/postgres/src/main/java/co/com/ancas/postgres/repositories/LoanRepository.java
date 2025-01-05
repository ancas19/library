package co.com.ancas.postgres.repositories;

import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.postgres.entities.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

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

    @Query(
            """
            SELECT new co.com.ancas.models.model.LoanInformation(
                l.id,
                b.isbn,
                b.title, 
                l.loanDate, 
                l.dueDate,
                l.returnDate,
                l.daysDelayed, 
                l.comments,
                l.fine,
                l.paid
             )
            FROM LoanEntity l
            INNER JOIN BooksEntity b ON l.bookId = b.id
            INNER JOIN UserEntity u ON l.userId = u.id
            INNER JOIN PeopleEntity p ON u.personId = p.id
            WHERE p.dni = :dni
            AND (LOWER(b.title) LIKE %:searchBook% OR b.isbn = :searchBook)
            AND (l.loanDate >= :startDate)
            AND (l.loanDate <= :finishDate)
            """
    )
    Page<LoanInformation> findLoansByUser(@Param("dni") String dni,@Param("searchBook") String searchBook,@Param("startDate") LocalDate startDate,@Param("finishDate") LocalDate finishDate, Pageable pageable);
}
