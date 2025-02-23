package co.com.ancas.controllers;

import co.com.ancas.request.PasswordRecoveryRequest;
import co.com.ancas.request.PeopleRequest;
import co.com.ancas.request.PersonAccessRequest;
import co.com.ancas.request.PersonCodeRequest;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.service.PeopleAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static co.com.ancas.models.enums.Messages.*;
import static co.com.ancas.models.enums.Messages.MESSAGE_PEOPLE_UNBLOCKED;

@Tag(name = "Account")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/account")
@Validated
public class AccountController {
    private final PeopleAppService peopleAppService;

    @PostMapping("/sign-up")
    @Operation(summary = "Create People", description = "Endpoint to create People")
    public ResponseEntity<GeneralResponse<PeopleResponse>> createPeople(
            @Valid @RequestBody PeopleRequest request
    ) throws MessagingException, IOException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        GeneralResponse.<PeopleResponse>builder()
                                .message(MESSAGE_PEOPLE_CREATED.getMessage())
                                .data(peopleAppService.createPeople(request))
                                .build()
                );
    }

    @PostMapping("/code")
    @Operation(summary = "Send code to unblock people or change password", description = "Endpoint to send code to unblock people")
    public ResponseEntity<GeneralResponse<String>> sendCodeToUnblockPeople(
            @Valid @RequestBody PersonAccessRequest personAccessRequest
    ) throws MessagingException, IOException {
        peopleAppService.sendCodeToUnblockPeople(personAccessRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<String>builder()
                                .message(MESSAGE_SEND_CODE.getMessage())
                                .data(MESSAGE_SEND_CODE.getMessage())
                                .build()
                );
    }

    @PostMapping("/access")
    @Operation(summary = "Unblock a person", description = "Endpoint to unblock a person")
    public ResponseEntity<GeneralResponse<String>> unblockPeople(
            @Valid @RequestBody PersonCodeRequest personCodeRequest
    ) throws MessagingException, IOException {
        peopleAppService.unblockPeople(personCodeRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<String>builder()
                                .message(MESSAGE_PEOPLE_UNBLOCKED.getMessage())
                                .data(MESSAGE_PEOPLE_UNBLOCKED.getMessage())
                                .build()
                );
    }

    @PutMapping("/passwords")
    @Operation(summary = "Change password", description = "Endpoint to change password")
    public ResponseEntity<GeneralResponse<String>> changePassword(
            @Valid @RequestBody PasswordRecoveryRequest passwordRecoveryRequest
    ) throws MessagingException, IOException {
        peopleAppService.recoveryPassword(passwordRecoveryRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<String>builder()
                                .message(MESSAGE_PASSWORD_CHANGED.getMessage())
                                .data(MESSAGE_PASSWORD_CHANGED.getMessage())
                                .build()
                );
    }
}
