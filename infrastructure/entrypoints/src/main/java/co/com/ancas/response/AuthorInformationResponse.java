package co.com.ancas.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorInformationResponse implements Serializable {
    private Long id;
    private String fullName;
    private String nationality;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate birthdate;
    private String bio;
    private String image;
}
