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
public class FileRequest {
    @NotNull(message = Constants.NAME_FILE_REQUIRED)
    @NotEmpty(message = Constants.NAME_FILE_REQUIRED)
    @Pattern(regexp = Constants.NAME_FILE, message = Constants.NAME_FILE_INVALID)
    private String nameFile;
    @NotNull(message = Constants.BASE64_REQUIRED)
    @NotNull(message = Constants.BASE64_REQUIRED)
    @NotEmpty(message =  Constants.BASE64_REQUIRED)
    private String base64;
    @NotNull(message = Constants.EXTENSION_REQUIRED)
    @NotEmpty(message = Constants.EXTENSION_REQUIRED)
    private String extension;
}
