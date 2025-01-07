package co.com.ancas.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonCodeRequest {
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    @Email
    private String email;
    @NotEmpty(message = "Code is required")
    @NotNull(message = "Code is required")
    @Pattern(regexp = "^\\d{6}$", message = "Code must have 6 digits and only numbers")
    private String code;
}
