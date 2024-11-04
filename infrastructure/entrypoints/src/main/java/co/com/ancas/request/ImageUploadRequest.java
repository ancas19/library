package co.com.ancas.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ImageUploadRequest {
    private Long id;
    private String nameFile;
    private String base64;
}
