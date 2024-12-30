package co.com.ancas.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookCreationRequest {
    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    @Pattern(regexp = "^[0-9A-ZÁÉÍÓÚÑ ]+$", message = "Title must have only letters and spaces")
    private String title;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(?:\\d{9}X|\\d{10}|\\d{13})$", message = "ISBN must have 10 or 13 digits")
    private String isbn;
    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ ]+$", message = "Name must have only letters and spaces")
    private String author;
    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate publishDate;
    @NotNull
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ ]+$", message = "Genre must have only letters and spaces")
    private String genre;
    @NotNull
    @PositiveOrZero
    private Integer availableCopies;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ0-9., @()_-]+$", message = "Bio must have only letters, spaces and special characters")
    private String blurb;
    @NotEmpty(message = "Name file is required")
    @Pattern(regexp = "^[a-zA-Z0-9.]+$", message = "Name file must have only letters and numbers")
    private String nameFile;
    @NotNull(message = "Base64 is required")
    @NotEmpty(message = "Base64 is required")
    private String base64;
}
