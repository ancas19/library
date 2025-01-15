package co.com.ancas.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PasswordRecoveryRequest {
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    @Email
    private String email;
    @NotEmpty(message = "Code is required")
    @NotNull(message = "Code is required")
    @Pattern(regexp = "^\\d{6}$", message = "Code must have 6 digits and only numbers")
    private String code;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must have at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must have at least one uppercase letter, one lowercase letter, one number and one special character")
    private String passwordRepeat;
}