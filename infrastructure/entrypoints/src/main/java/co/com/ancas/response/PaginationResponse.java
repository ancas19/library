package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PaginationResponse<T> implements Serializable {
    private List<T> content;
    private Long totalElements;
    private Integer totalPages;
    private Integer numberPage;
    private Integer size;
    private boolean last;
}
