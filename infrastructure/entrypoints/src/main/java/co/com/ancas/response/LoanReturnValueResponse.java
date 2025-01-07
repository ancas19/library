package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanReturnValueResponse implements Serializable {
    private Long idLoan;
    private String title;
    private String isbn;
    private Integer daysOverdue;
    private Double valueToPay;
}
