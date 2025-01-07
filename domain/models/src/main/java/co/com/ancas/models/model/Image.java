package co.com.ancas.models.model;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Image {
    private Long id;
    private String fileName;
    private String filePath;
    private String idServer;
}
