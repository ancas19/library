package co.com.ancas.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanReturnRequest {
    @NotNull
    @Positive
    private Long idLoan;
    @NotNull
    @PositiveOrZero
    private Double fine;
    @Pattern(regexp = "^[\\p{L}0-9.,:;!?()'\" \\t\\n\\-]+$\n", message = "Comment must be a valid string")
    private String comment;
}
