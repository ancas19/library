package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
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
    @NotEmpty(message=Constants.EMAIL_REQUIRED)
    @NotNull(message=Constants.EMAIL_REQUIRED)
    @Email
    private String email;
}
