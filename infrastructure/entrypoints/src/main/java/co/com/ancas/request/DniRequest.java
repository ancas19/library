package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
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
    @Pattern(regexp = Constants.DNI, message = Constants.DNI_INVALID)
    private String dni;
}
