package co.com.ancas.controllers;

import co.com.ancas.request.AuthorCreationRequest;
import co.com.ancas.response.AuthorInformationResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.service.AuthorAppService;
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


@Validated
@RestController
@Tag(name = "Authors")
@RequiredArgsConstructor
@RequestMapping("/v1.0/account")
public class AuthorsController {
    private final AuthorAppService authorAppService;


    @PostMapping()
    public ResponseEntity<GeneralResponse<AuthorInformationResponse>> createAuthpr(
            @Valid @RequestBody AuthorCreationRequest authorCreationRequest) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<AuthorInformationResponse>builder()
                        .message("Author created successfully")
                        .data(authorAppService.createAuthor(authorCreationRequest))
                        .build()
        );
    }
}
