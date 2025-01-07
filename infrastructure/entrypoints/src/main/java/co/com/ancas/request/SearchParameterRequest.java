package co.com.ancas.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class SearchParameterRequest {
    @NotNull(message = "Search parameter is required")
    @Pattern(regexp = "^[A-Za-z0-9 ]*$", message = "Search parameter must have only letters, numbers and spaces")
    private String search;
}
