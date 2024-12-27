package co.com.ancas.request;

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
    @NotNull(message = "Name file is required")
    @NotEmpty(message = "Name file is required")
    @Pattern(regexp = "^[a-zA-Z0-9.]+$", message = "Name file must have only letters and numbers")
    private String nameFile;
    @NotNull(message = "Base64 is required")
    @NotEmpty(message = "Base64 is required")
    private String base64;
    @NotNull
    @Positive
    private Long id;
}
