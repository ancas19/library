package co.com.ancas.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookSearchCriteriaRequest {
    @NotNull
    @Pattern(regexp = "^[0-9a-zA-ZáéíóúÁÉÍÓÚÑ ]*$", message = "Search must have only letters, digits and spaces")
    private String search;
    @NotNull
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ ]*$", message = "Name must have only letters and spaces")
    private String author;
    @NotNull
    @Pattern(regexp = "^[A-ZÁÉÍÓÚÑ ]*$", message = "Genre must have only letters and spaces")
    private String genre;
}
