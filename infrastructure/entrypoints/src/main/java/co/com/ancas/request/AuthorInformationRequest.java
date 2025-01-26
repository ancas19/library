package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorInformationRequest {
    @NotNull(message = Constants.ID_REQUIRED)
    @Positive(message = Constants.ID_INVALID)
    private Long id;
    @NotNull(message = Constants.NAME_REQUIRED)
    @NotEmpty(message = Constants.NAME_REQUIRED)
    @Pattern(regexp = Constants.LETTERS, message = Constants.NAME_INVALID)
    private String fullName;
    @NotNull(message = Constants.NATIONALITY_REQUIRED)
    @NotEmpty(message = Constants.NATIONALITY_REQUIRED)
    @Pattern(regexp = Constants.NATIONALITY, message = Constants.NATIONALITY_INVALID)
    private String nationality;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate birthdate;
    @NotNull
    @NotEmpty
    @Pattern(regexp = Constants.LETTERS_AND_SPECIAL_CHARACTERS, message = Constants.BIO_INVALID)
    private String bio;
}
