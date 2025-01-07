package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class AuthToken {
    private String message;
    private String token;
    private PeopleFullInfomration peopleFullInfomration;
}
