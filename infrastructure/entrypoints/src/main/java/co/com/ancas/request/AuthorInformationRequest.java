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
public class AuthorInformationRequest {
    @NotNull(message = "Id is required")
    @Positive(message = "Id must be positive")
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
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ0-9., @()_-]+$", message = "Bio must have only letters, spaces and special characters")
    private String bio;
}
