package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
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
    @NotNull(message = Constants.DNI_REQUIRED)
    @NotEmpty(message = Constants.DNI_REQUIRED)
    @Pattern(regexp = Constants.DNI, message = Constants.DNI_INVALID)
    private String dni;
    @NotNull(message = Constants.NAME_REQUIRED)
    @NotEmpty(message = Constants.NAME_REQUIRED)
    @Pattern(regexp = Constants.LETTERS, message = Constants.NAME_INVALID)
    private String firstName;
    @NotNull(message = Constants.LAST_NAME_REQUIRED)
    @NotEmpty(message = Constants.LAST_NAME_REQUIRED)
    @Pattern(regexp = Constants.LETTERS, message = Constants.LAST_NAME_INVALID)
    private String lastName;
    @NotEmpty(message = Constants.EMAIL_REQUIRED)
    @NotNull(message = Constants.EMAIL_REQUIRED)
    @Email
    private String email;
    @NotNull(message = Constants.PHONE_REQUIRED)
    @Pattern(regexp = Constants.PHONE, message = Constants.PHONE_INVALID)
    private String phone;
    @Pattern(regexp = Constants.ROLE, message = Constants.ROLE_INVALID)
    @NotNull
    private String userType;
}
