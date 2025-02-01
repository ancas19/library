package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
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
    @NotEmpty(message = Constants.EMAIL_REQUIRED)
    @NotNull(message =  Constants.EMAIL_REQUIRED)
    @Email
    private String email;
    @NotEmpty(message = Constants.CODE_REQUIRED)
    @NotNull(message = Constants.CODE_REQUIRED)
    @Pattern(regexp = Constants.CODE, message = Constants.CODE_INVALID)
    private String code;
    @NotNull(message = Constants.PASSWORD_REQUIRED)
    @NotEmpty(message =  Constants.PASSWORD_REQUIRED)
    @Pattern(regexp = Constants.REGEX_PASS, message = Constants.PASSWORD_INVALID)
    private String password;
    @NotNull(message = Constants.CONFIRM_PASSWORD_REQUIRED)
    @NotEmpty(message =  Constants.CONFIRM_PASSWORD_REQUIRED)
    @Pattern(regexp = Constants.REGEX_PASS, message = Constants.PASSWORD_INVALID)
    private String passwordRepeat;
}