package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdatePassword {
    private String username;
    private String password;
    private String confirmPassword;
}
