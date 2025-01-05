package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanDetailsResponse  implements Serializable {
    private Long id;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private Integer daysDelayed;
    private String isbn;
    private String title;
}
