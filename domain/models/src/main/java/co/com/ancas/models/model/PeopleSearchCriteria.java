package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleSearchCriteria {
    private String search;
    private String role;
    private int page;
    private int size;


    @Override
    public String toString() {
        return String.format("search:%s|role:%s|page:%d|size:%d", search, role, page, size);
    }
}
