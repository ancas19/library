package co.com.ancas.request;

import co.com.ancas.models.enums.TypeSearch;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanSearchByUserRequest {
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^[0-9]{10,15}$", message = "DNI must have beetween 10 and 15 digits")
    private String dni;
    @NotNull
    @Pattern(regexp = "^[0-9a-záéíóúñ ]*$", message = "Search book only can have letters, numbers, spaces and accents")
    private String searchBook;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate finishDate;
    @NotNull
    @Pattern(regexp = "HISTORICAL|RETURNED|EXPIRED|ACTIVE", message = "Type search must be HISTORICAL, RETURNED, EXPIRED or ACTIVE")
    private String typeSearch;
}
