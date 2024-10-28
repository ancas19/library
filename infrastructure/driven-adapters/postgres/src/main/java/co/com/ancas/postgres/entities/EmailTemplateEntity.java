package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "email_templates")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class EmailTemplateEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "body", nullable = false)
    private String body;
}
