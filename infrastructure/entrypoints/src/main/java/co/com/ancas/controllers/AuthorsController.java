package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.AuthorCreationRequest;
import co.com.ancas.request.SearchParameterRequest;
import co.com.ancas.response.AuthorInformationResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.service.AuthorAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Validated
@RestController
@Tag(name = "Authors")
@RequiredArgsConstructor
@RequestMapping("/v1.0/authors")
public class AuthorsController {
    private final AuthorAppService authorAppService;


    @PostMapping()
    public ResponseEntity<GeneralResponse<AuthorInformationResponse>> createAuthpr(
            @Valid @RequestBody AuthorCreationRequest authorCreationRequest) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<AuthorInformationResponse>builder()
                        .message(Messages.MESSAGE_AUTHOR_CREATED.getMessage())
                        .data(authorAppService.createAuthor(authorCreationRequest))
                        .build()
        );
    }

    @PostMapping("/all")
    public ResponseEntity<GeneralResponse<PaginationResponse<AuthorInformationResponse>>> findAuthors(
            @Valid @RequestBody SearchParameterRequest searchParameterRequest,
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<PaginationResponse<AuthorInformationResponse>>builder()
                        .message(Messages.MESSAGE_AUTHOR_FOUND.getMessage())
                        .data(authorAppService.findAuthors(searchParameterRequest, page, size))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse<AuthorInformationResponse>> findById(
            @PathVariable Long id
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<AuthorInformationResponse>builder()
                        .message(Messages.MESSAGE_AUTHOR_FOUND.getMessage())
                        .data(authorAppService.findById(id))
                        .build()
        );
    }
}
