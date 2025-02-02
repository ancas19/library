package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorsSearchCriteria {
    private String search;
    private int page;
    private int size;

    @Override
    public String toString() {
        return String.format("search:%s|page:%d|size:%d", search, page, size);
    }
}
