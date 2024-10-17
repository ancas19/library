package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.response.GeneralResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RestController
@Tag(name = "Health")
public class HealthController {

    @GetMapping()
    public ResponseEntity<GeneralResponse<String>> health() {
        return ResponseEntity.ok(
                GeneralResponse.<String>builder()
                        .message(Messages.MESSAGE_HEALTH_CHECK.getMessage())
                        .data(Messages.MESSAGE_HEALTH_CHECK.getMessage())
                        .build()
        );
    }
}


