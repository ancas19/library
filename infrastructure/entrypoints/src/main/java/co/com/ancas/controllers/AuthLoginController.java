package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
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
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Auth")
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

    @PostMapping("/logout")
    public ResponseEntity<GeneralResponse<String>> logout(
            @RequestParam("Authorization") String token
    ) {
        token = token.replace("Bearer ", "");
        authService.logout(token);
        return ResponseEntity.ok(
                GeneralResponse.<String>builder()
                        .message(Messages.MESSAGE_LOGOUT_SUCCESSFUL.getMessage())
                        .data(Messages.MESSAGE_LOGOUT_SUCCESSFUL.getMessage())
                        .build()
        );
    }

}
