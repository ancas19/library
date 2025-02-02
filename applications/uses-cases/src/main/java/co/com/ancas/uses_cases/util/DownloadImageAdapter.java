package co.com.ancas.uses_cases.util;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

@Slf4j
@Component
@RequiredArgsConstructor
public class DownloadImageAdapter {

    public String downloadImage(String url) {
        try {
            InputStream inputStream= new URL(url).openStream();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();

            byte[] imageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (Exception e) {
            log.error("Error downloading image", e);
            throw new BadRequestException(Messages.MESSAGE_ERROR_DOWNLOAD_IMAGE.getMessage());
        }

    }
}

