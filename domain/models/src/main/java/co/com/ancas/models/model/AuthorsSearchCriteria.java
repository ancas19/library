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
        return "AuthorsSearchCriteria{" +
                "search='" + search + '\'' +
                ", page=" + page +
                ", size=" + size +
                '}';
    }
}
