package co.com.ancas.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PersonAccessRequest {
    @NotEmpty(message = "Email is required")
    @NotNull(message = "Email is required")
    @Email
    private String email;
}
