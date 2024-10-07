package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "memberships")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MembershipEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "membership_type", nullable = false)
    private String membershipType;

    @Column(name = "loan_limit", nullable = false)
    private Integer loanLimit;

    @Column(name = "loan_period_days", nullable = false)
    private Integer loanPeriodDays;

    @Column(name = "grace_period_days", nullable = false)
    private Integer gracePeriodDays;
}
