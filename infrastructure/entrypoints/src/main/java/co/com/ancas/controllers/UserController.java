package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.ChangePasswordRequest;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.service.UserAppService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static co.com.ancas.models.enums.Messages.MESSAGE_PASSWORD_UPDATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/users")
public class UserController {
    private final UserAppService userAppService;

    @PatchMapping("/password")
    public ResponseEntity<GeneralResponse<String>> changePassword(
            @Valid @RequestBody ChangePasswordRequest request
    ) throws MessagingException, IOException {
        this.userAppService.updatePassword(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<String>builder()
                                .message(MESSAGE_PASSWORD_UPDATED.getMessage())
                                .data(MESSAGE_PASSWORD_UPDATED.getMessage())
                                .build()
                );
    }

}
