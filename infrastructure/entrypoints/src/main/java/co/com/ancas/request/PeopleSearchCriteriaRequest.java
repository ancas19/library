package co.com.ancas.request;
import co.com.ancas.models.utils.Constants;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleSearchCriteriaRequest implements Serializable {
    @NotNull(message =Constants.SEARCH_REQUIRED)
    @Pattern(regexp =  Constants.SEARCH, message = Constants.SEARCH_INVALID)
    private String search;
    @NotNull(message = Constants.ROLE_REQUIRED)
    @NotEmpty(message = Constants.ROLE_REQUIRED)
    @Pattern(regexp = Constants.ROLE, message = Constants.ROLE_INVALID)
    private String role;
}
