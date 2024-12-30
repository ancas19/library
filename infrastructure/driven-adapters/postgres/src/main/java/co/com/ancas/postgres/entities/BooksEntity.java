package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(name = "author_id", nullable = false)
    private Long authorId;
    @Column(name = "publish_date")
    private LocalDate publishDate;
    @Column(name = "genre_id", nullable = false)
    private Long genreId;
    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;
    @Column(name = "blurb",nullable = false)
    private String blurb;
    @Column(name = "image_id", nullable = false)
    private Long imageId;
}
