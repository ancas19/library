package co.com.ancas.models.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanDetails {
    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Integer daysDelayed;
    private String isbn;
    private String title;
}
