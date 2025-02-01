package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TokenInformation {
    private String token;
    private String username;
    private String email;
    private String dni;
    private String role;
    private Long idPersona;
    private Long idUser;
}
