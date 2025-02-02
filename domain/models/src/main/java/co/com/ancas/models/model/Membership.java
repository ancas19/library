package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Membership {
    private Long id;
    private String membershipType;
    private Integer loanLimit;
    private Integer loanPeriodDays;
    private Integer gracePeriodDays;
    private Double finePerDay;

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", membershipType='" + membershipType + '\'' +
                ", loanLimit=" + loanLimit +
                ", loanPeriodDays=" + loanPeriodDays +
                ", gracePeriodDays=" + gracePeriodDays +
                ", finePerDay=" + finePerDay +
                '}';
    }
}
