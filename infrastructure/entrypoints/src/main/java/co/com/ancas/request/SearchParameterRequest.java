package co.com.ancas.request;


import co.com.ancas.models.utils.Constants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SearchParameterRequest {
    @NotNull(message = Constants.SEARCH_PARAMETERS_REQUIRED)
    @Pattern(regexp = Constants.SEARCH_PARAMETERS, message = Constants.SEARCH_PARAMETERS_INVALID)
    private String search;
}
