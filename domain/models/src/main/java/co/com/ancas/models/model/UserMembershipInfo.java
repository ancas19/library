package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserMembershipInfo {
    private Long userId;
    private String username;
    private String email;
    private String membershipType;
    private Integer loanLimit;
    private Integer loanPeriodDays;
    private Integer gracePeriodDays;
    private Double dailyFine;

}
