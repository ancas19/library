package co.com.ancas.request;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleSearchCriteriaRequest implements Serializable {
    @NotNull(message = "Search is required")
    @Pattern(regexp = "^[0-9a-zA-ZáéíóúÁÉÍÓÚÑ ]*$", message = "Search must have only letters, digits and spaces")
    private String search;
    @NotNull(message = "Role is required")
    @NotEmpty(message = "Role is required")
    @Pattern(regexp = "ADMIN|USER|EMPLOYEE", message = "Role must be 'ADMIN', 'EMPLOYEE' or 'USER'")
    private String role;
}
