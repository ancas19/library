package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class PasswordRecovery {
    private String email;
    private String code;
    private String password;
    private String passwordRepeat;
}
