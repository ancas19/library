package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RolesEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role_name", nullable = false)
    private String roleName;
}
