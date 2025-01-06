package co.com.ancas.models.model;

import co.com.ancas.models.enums.TypeSearch;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanSearchByUser {
    private String dni;
    private String searchBook;
    private LocalDate startDate;
    private LocalDate finishDate;
    private TypeSearch typeSearch;
    private Integer page;
    private Integer size;
}
