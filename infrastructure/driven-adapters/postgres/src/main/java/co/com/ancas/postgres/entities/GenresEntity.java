package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "genres")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GenresEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "value",nullable = false)
    private String value;
}
