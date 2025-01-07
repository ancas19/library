package co.com.ancas.request;

import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleInformationRequest {
    @NotNull(message = "Id is required")
    @Positive(message = "Id is required and must be positive")
    private Long id;
    @NotNull(message = "DNI is required")
    @NotEmpty(message = "DNI is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "DNI must have beetween 10 and 15 digits")
    private String dni;
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ ]+$", message = "Name must have only letters and spaces")
    private String firstName;
    @NotNull(message = "Last name is required")
    @NotEmpty(message = "Last name is required")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ ]+$", message = "Last name must have only letters and spaces")
    private String lastName;
    @Pattern(regexp = "^\\+[1-9][0-9]{1,3}[0-9]{6,14}$", message = "Phone must have 10 digits")
    private String phone;
}
