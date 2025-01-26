package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ImageUploadRequest {
    @NotNull(message = Constants.NAME_FILE_REQUIRED)
    @NotEmpty(message = Constants.NAME_FILE_REQUIRED)
    @Pattern(regexp = Constants.NAME_FILE, message = Constants.NAME_FILE_INVALID)
    private String nameFile;
    @NotNull(message = Constants.BASE64_REQUIRED)
    @NotNull(message = Constants.BASE64_REQUIRED)
    @NotEmpty(message =  Constants.BASE64_REQUIRED)
    private String base64;
    @NotNull
    @Positive
    private Long id;
}
