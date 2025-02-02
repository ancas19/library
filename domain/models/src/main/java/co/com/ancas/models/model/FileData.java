package co.com.ancas.models.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FileData {
    private String nameFile;
    private String base64;
    private String extension;
}
