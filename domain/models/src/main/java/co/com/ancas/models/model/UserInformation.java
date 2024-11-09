package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserInformation {
    private Long id;
    private String username;
    private String role;
    private String membership;
    private boolean emailVerified;
    private boolean changePassword;

    @Override
    public String toString() {
        return "UserInformation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", membership='" + membership + '\'' +
                ", emailVerified=" + emailVerified +
                ", changePassword=" + changePassword +
                '}';
    }
}
