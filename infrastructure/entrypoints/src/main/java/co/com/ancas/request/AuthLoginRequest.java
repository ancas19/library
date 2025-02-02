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
public class AuthLoginRequest {
    @NotNull(message = Constants.USERNAME_REQUIRED)
    @NotEmpty(message = Constants.USERNAME_REQUIRED)
    @Pattern(regexp =Constants.USERNAME, message = Constants.USERNAME_INVALID)
    private String username;
    @NotNull(message =Constants.PASSWORD_REQUIRED )
    @NotEmpty(message = Constants.PASSWORD_REQUIRED)
    @Pattern(regexp = Constants.REGEX_PASS, message = Constants.PASSWORD_INVALID)
    private String password;
}
