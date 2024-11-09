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
public class ChangePasswordRequest {
    @NotNull(message = "Username is required")
    @NotEmpty(message = "Username is required")
    @Pattern(regexp = "^[A-Z0-9.]+$", message = "Username must have only letters and numbers")
    private String username;
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must have at least one uppercase letter, one lowercase letter, one number and one special character")
    private String password;
    @NotNull(message = "Confirm password is required")
    @NotEmpty(message = "Confirm password is required")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must have at least one uppercase letter, one lowercase letter, one number and one special character")
    private String confirmPassword;
}
