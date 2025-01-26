package co.com.ancas.request;

import co.com.ancas.models.enums.TypeSearch;
import co.com.ancas.models.utils.Constants;
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
    @Pattern(regexp = Constants.DNI, message = Constants.DNI_INVALID)
    private String dni;
    @NotNull
    @Pattern(regexp = Constants.SEARCH_BOOK, message = Constants.SEARCH_BOOK_INVALID)
    private String searchBook;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate finishDate;
    @NotNull
    @Pattern(regexp =Constants.TYPE_SEARCH , message = Constants.TYPE_SEARCH_INVALID)
    private String typeSearch;
}
