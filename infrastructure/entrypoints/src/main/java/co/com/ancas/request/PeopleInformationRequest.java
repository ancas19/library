package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
import jakarta.validation.constraints.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleInformationRequest {
    @NotNull()
    @Positive()
    private Long id;
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
    @NotNull(message = Constants.PHONE_REQUIRED)
    @Pattern(regexp = Constants.PHONE, message = Constants.PHONE_INVALID)
    private String phone;
}
