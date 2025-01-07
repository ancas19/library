package co.com.ancas.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanRequest {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]{10,15}$", message = "DNI must have beetween 10 and 15 digits")
    private String dni;
    @NotNull
    private List<LoanInfoRequest> loanInfo;
}
