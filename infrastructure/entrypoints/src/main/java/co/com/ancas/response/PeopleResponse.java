package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleResponse implements Serializable {
    private Long id;
    private String dni;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String status;
}
