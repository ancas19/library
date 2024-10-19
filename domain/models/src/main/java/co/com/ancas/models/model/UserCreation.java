package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserCreation {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userType;
}
