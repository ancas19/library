package co.com.ancas.response;

import co.com.ancas.models.model.PeopleFullInfomration;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthTokenResponse  implements Serializable {
    private String message;
    private String token;
    private PeopleFullInfomration peopleFullInfomration;
}
