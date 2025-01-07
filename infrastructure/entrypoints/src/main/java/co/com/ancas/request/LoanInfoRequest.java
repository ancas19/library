package co.com.ancas.request;

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
    @Pattern(regexp = "^(?:\\d{9}X|\\d{10}|\\d{13})$", message = "ISBN must have 10 or 13 digits")
    private String isbn;
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate loanDate;
    @NotNull
    @Positive
    private Integer quantity;
}
