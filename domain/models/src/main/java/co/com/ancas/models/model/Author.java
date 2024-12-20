package co.com.ancas.models.model;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Author {
    private Long id;
    private String fullName;
    private String nationality;
    private LocalDate birthdate;
    private String bio;
    private Long imageId;
}
