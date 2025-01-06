package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanReturn {
    private Long idLoan;
    private Double fine;
    private String comment;
}
