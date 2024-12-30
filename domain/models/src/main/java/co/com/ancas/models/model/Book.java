package co.com.ancas.models.model;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Book {
    private Long id;
    private String title;
    private String isbn;
    private Long authorId;
    private LocalDate publishDate;
    private Long genreId;
    private Integer availableCopies;
    private String blurb;
    private Long imageId;
    private String available;
}
