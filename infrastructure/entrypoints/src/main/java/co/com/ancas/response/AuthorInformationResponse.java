package co.com.ancas.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorInformationResponse {
    private String fullName;
    private String nationality;
    private LocalDate birthdate;
    private String bio;
    private String image;
}
