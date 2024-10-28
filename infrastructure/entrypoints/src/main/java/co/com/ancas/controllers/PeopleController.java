package co.com.ancas.controllers;

import co.com.ancas.request.PeopleRequest;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "People")
@RestController
@RequiredArgsConstructor
@RequestMapping("/people")
@Validated
public class PeopleController {
    private final PeopleAppService peopleService;
    @PostMapping
    @Operation(summary = "Create People", description = "Endpoint to create People")
    public ResponseEntity<GeneralResponse<PeopleResponse>> createPeople(
            @Valid @RequestBody PeopleRequest request
    ) throws MessagingException {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        GeneralResponse.<PeopleResponse>builder()
                                .message("People created successfully")
                                .data(peopleService.createPeople(request))
                                .build()
                );
    }
}
