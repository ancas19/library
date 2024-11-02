package co.com.ancas.models.model;

import lombok.*;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PeopleSearchCriteria {
    private String search;
    private String role;
    private Pageable pageable;
}
