package co.com.ancas.request;

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
public class AuthorCreationRequest {
    @Positive
    private Long id;
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ ]+$", message = "Name must have only letters and spaces")
    private String fullName;
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-Z ]+$", message = "Name must have only letters and spaces")
    private String nationality;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate birthdate;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ0-9. @()]+$", message = "Name must have only letters and spaces")
    private String bio;
    @NotNull(message = "Name file is required")
    @NotEmpty(message = "Name file is required")
    @Pattern(regexp = "^[A-Z0-9.]+$", message = "Name file must have only letters and numbers")
    private String nameFile;
    @NotNull(message = "Base64 is required")
    @NotEmpty(message = "Base64 is required")
    private String base64;
}
