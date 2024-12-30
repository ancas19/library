package co.com.ancas.models.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookUpdate {
    private Long id;
    private String title;
    private String isbn;
    private String author;
    private LocalDate publishDate;
    private String genre;
    private Integer availableCopies;
    private String blurb;
}
