package co.com.ancas.models.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class LoanReturnResult {
    private Integer delayedDays;
    private Double total;
}
