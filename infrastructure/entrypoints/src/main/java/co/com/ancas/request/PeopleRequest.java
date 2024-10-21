package co.com.ancas.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PeopleRequest {
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
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    @Email
    private String email;
    @Pattern(regexp = "^\\+[1-9][0-9]{1,3}[0-9]{6,14}$", message = "Phone must have 10 digits")
    private String phone;
    @Pattern(regexp = "EMPLOYEE|USER", message = "User type must be 'EMPLOYEE' or 'USER'")
    @NotNull
    private String userType;
}
