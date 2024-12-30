package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "loan_date")
    private LocalDate loanDate;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "days_delayed")
    private Integer daysDelayed;

    @Column(name = "comments")
    private String comments;

    @Column(name = "fine")
    private Double fine;

    @Column(name = "paid")
    private String paid;
}

