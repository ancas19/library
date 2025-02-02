package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
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
    @Pattern(regexp = Constants.COMMENT, message = Constants.COMMENT_INVALID)
    private String comment;
}
