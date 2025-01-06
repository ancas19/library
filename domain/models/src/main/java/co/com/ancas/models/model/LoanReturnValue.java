package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanReturnValue {
    private Long idLoan;
    private String title;
    private String isbn;
    private Integer daysOverdue;
    private Double valueToPay;
}
