package co.com.ancas.request;

import co.com.ancas.models.utils.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookCreationRequest {
    @NotNull(message = Constants.TITLE_REQUIRED)
    @NotEmpty(message = Constants.TITLE_REQUIRED)
    @Pattern(regexp = Constants.LETTERS_AND_NUMBERS, message = Constants.TITLE_INVALID)
    private String title;
    @NotNull
    @NotEmpty
    @Pattern(regexp =Constants.ISBN, message = "ISBN must have 10 or 13 digits")
    private String isbn;
    @NotNull
    @Past
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING, timezone = "America/Bogota")
    private LocalDate publishDate;
    @NotNull(message = Constants.AUTHOR_REQUIRED)
    @NotEmpty(message = Constants.AUTHOR_REQUIRED)
    @Pattern(regexp = Constants.LETTERS, message = Constants.AUTHOR_INVALID)
    private String author;
    @NotNull
    @Pattern(regexp =Constants.LETTERS, message = Constants.GENRE_INVALID)
    private String genre;
    @NotNull()
    @PositiveOrZero
    private Integer availableCopies;
    @NotNull
    @NotEmpty
    @Pattern(regexp =Constants.AVAILABLE, message = Constants.AVAILABLE_INVALID)
    private String available;
    @NotNull
    @NotEmpty
    @Pattern(regexp = Constants.LETTERS_AND_SPECIAL_CHARACTERS, message = Constants.BLURB_INVALID)
    private String blurb;
    @NotEmpty(message = Constants.NAME_FILE_REQUIRED)
    @Pattern(regexp = Constants.NAME_FILE, message = Constants.NAME_FILE_INVALID)
    private String nameFile;
    @NotNull(message = Constants.BASE64_REQUIRED)
    @NotEmpty(message =  Constants.BASE64_REQUIRED)
    private String base64;
}
