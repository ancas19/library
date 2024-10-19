package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleCreation {
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String userType;
}
