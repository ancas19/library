package co.com.ancas.models.model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookInformation {
    private Long id;
    private String title;
    private String isbn;
    private LocalDate publishDate;
    private String genre;
    private Integer availableCopies;
    private String blurb;
    private String bookImage;
    private String author;
    private String authorImage;
    private String available;
}
