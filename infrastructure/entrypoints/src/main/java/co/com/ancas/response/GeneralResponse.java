package co.com.ancas.response;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GeneralResponse<T> implements Serializable {
    private String message;
    private T data;
}
