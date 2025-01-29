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
    private boolean changePassword;
    private PeopleFullInfomration peopleFullInfomration;
}
