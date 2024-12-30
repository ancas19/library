package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookInformationResponse implements Serializable {
    private Long id;
    private String title;
    private String isbn;
    private LocalDate publishDate;
    private String genre;
    private Integer availableCopies;
    private String blurb;
    private String booImage;
    private String author;
    private String authorImage;
}
