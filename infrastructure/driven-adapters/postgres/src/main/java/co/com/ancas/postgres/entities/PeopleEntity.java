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
public class PeopleEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String dni;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(name = "profile_image")
    private Integer profileImage;
}
