package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanInfoRequest {
    @NotNull
    @NotEmpty
    @Pattern(regexp = Constants.ISBN, message =Constants.ISBN_INVALID)
    private String isbn;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate loanDate;
    @NotNull
    @Positive
    private Integer quantity;
}
