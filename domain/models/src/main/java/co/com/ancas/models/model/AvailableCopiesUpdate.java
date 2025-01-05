package co.com.ancas.models.model;


import co.com.ancas.models.enums.Constants;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AvailableCopiesUpdate {
    private Long bookId;
    private Integer copies;
    private Constants action;
}
