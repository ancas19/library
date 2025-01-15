package co.com.ancas.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DniRequest {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^\\d{8,15}$", message = "DNI must be a valid number with 8 to 10 digits")
    private String dni;
}
