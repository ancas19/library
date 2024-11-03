package co.com.ancas.utils;

import co.com.ancas.models.utils.Mapper;
import co.com.ancas.response.PaginationResponse;
import org.springframework.data.domain.Page;

public class Pagination {
    private Pagination(){}
    public static <T> PaginationResponse<T> getPaginationResponse(Page<?> objects, Class<T> targetType) {
        return PaginationResponse.<T>builder()
                .content(Mapper.mapAll(objects.getContent(),targetType))
                .totalPages(objects.getTotalPages())
                .totalElements(objects.getTotalElements())
                .numberPage(objects.getNumber())
                .size(objects.getSize())
                .last(objects.isLast())
                .build();
    }
}
