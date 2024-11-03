package co.com.ancas.response;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PaginationResponse<T> {
    private List<T> content;
    private Long totalElements;
    private Integer totalPages;
    private Integer numberPage;
    private Integer size;
    private boolean last;
}
