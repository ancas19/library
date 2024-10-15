package co.com.ancas.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GeneralResponse<T> {
    private String message;
    private T data;
}
