package co.com.ancas.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResponse implements Serializable {
    private LocalDate timeStamp;
    private String message;
    private String details;
}
