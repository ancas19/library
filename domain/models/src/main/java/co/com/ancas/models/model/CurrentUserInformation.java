package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CurrentUserInformation  {
    private String username;
    private Long userId;
    private Long personId;
    private String dni;
    private String role;
}
