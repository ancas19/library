package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    private Long id;
    private Long personId;
    private String username;
    private String password;
    private Long roleId;
    private Long membershipId;
    private boolean emailVerified;
    private boolean changePassword;
}
