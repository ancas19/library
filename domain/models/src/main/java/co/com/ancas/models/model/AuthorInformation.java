package co.com.ancas.models.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorInformation {
    private String fullName;
    private String nationality;
    private LocalDate birthdate;
    private String bio;
    private String image;
}
