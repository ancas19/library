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
public class BookSearchCriteriaRequest {
    @NotNull
    @Pattern(regexp = Constants.SEARCH, message = Constants.SEARCH_INVALID)
    private String search;
    @NotNull
    @Pattern(regexp = Constants.LETTERS, message = Constants.AUTHOR_INVALID)
    private String author;
    @NotNull
    @Pattern(regexp =  Constants.LETTERS, message = Constants.GENRE_INVALID)
    private String genre;
}
