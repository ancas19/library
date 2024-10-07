package co.com.ancas.postgres.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer authorId;

    @Column(name = "publish_date")
    private String publishDate;

    @Column
    private String genre;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

    @Column(name = "image_id", nullable = false)
    private Integer imageId;
}
