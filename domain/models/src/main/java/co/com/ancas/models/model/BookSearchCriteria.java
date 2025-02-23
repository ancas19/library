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
        return String.format("search:%s|author:%s|genre:%s|page:%d|size:%d", search, author, genre, page, size);
    }
}
