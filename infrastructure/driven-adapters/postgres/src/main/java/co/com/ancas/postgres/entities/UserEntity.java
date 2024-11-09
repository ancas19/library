package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "membership_id", nullable = false)
    private Long membershipId;

    @Column(name = "email_verified", nullable = false)
    private boolean emailVerified;

    @Column(name = "change_password", nullable = false)
    private boolean changePassword;
}
