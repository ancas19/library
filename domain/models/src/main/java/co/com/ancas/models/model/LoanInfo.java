package co.com.ancas.models.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanInfo {
    private String isbn;
    private LocalDate loanDate;
    private Integer quantity;
}
