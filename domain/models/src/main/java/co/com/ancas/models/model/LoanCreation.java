package co.com.ancas.models.model;


import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanCreation {
    private String dni;
    private List<LoanInfo> details;
}
