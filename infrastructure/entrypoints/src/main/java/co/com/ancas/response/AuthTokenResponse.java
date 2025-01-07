package co.com.ancas.response;

import co.com.ancas.models.model.PeopleFullInfomration;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthTokenResponse {
    private String message;
    private String token;
    private PeopleFullInfomration peopleFullInfomration;
}
