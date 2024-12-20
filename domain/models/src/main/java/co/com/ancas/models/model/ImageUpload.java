package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ImageUpload {
    private String nameFile;
    private String base64;
    private Long idImage;
}

