package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookSearchCriteria {
    private String search;
    private String author;
    private String genre;
    private int page;
    private int size;

    @Override
    public String toString() {
        return "BookSearchCriteria{" +
                "search='" + search + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
