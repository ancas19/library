package co.com.ancas.controllers;

import co.com.ancas.request.AuthLoginRequest;
import co.com.ancas.response.AuthTokenResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.service.AuthAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "People")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/auth")
@Validated
public class AuthLoginController {
    private final AuthAppService authService;
    @PostMapping("/login")
    public ResponseEntity<GeneralResponse<AuthTokenResponse>> login(
            @Valid @RequestBody AuthLoginRequest request
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<AuthTokenResponse>builder()
                        .data(authService.login(request))
                        .message("Login successful")
                        .build()
        );
    }

}
