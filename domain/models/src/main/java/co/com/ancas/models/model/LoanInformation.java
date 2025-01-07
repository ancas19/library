package co.com.ancas.models.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanInformation {
    private Long id;
    private String isbn;
    private String title;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private Integer daysDelayed;
    private String comments;
    private Double fine;
    private String paid;
}
