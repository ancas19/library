package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "authors")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorsEntity extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(name = "nationality")
    private String nationality;
    @Column(name = "birthdate")
    private LocalDate birthdate;
    @Column(name = "bio")
    private String bio;
    @Column(name = "image_id")
    private Long imageId;
}
