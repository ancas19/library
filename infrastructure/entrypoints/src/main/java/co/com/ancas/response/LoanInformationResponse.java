package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanInformationResponse implements Serializable {
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
