package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoanReturnResultResponse  implements Serializable {
    private Integer delayedDays;
    private Double total;
}
