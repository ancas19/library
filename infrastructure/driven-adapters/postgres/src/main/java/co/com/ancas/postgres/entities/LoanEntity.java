package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer userId;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "loan_date")
    private String loanDate;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "return_date")
    private String returnDate;
}
