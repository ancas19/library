package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInformationResponse implements Serializable {
    private Long id;
    private String username;
    private String role;
    private String membership;
    private boolean emailVerified;
    private boolean changePassword;
}
