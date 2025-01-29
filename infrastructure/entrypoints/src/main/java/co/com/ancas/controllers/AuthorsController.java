package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.*;
import co.com.ancas.response.AuthorInformationResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.service.AuthorAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN','EMPLOYEE')")
    @Operation(summary = "Create a new author")
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
    @Operation(summary = "Find all authors")
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

    @PutMapping("/information")
    @PreAuthorize("hasRole('ADMIN','EMPLOYEE')")
    @Operation(summary = "Update author information")
    public ResponseEntity<GeneralResponse<AuthorInformationResponse>> updateAuthor(
            @Valid @RequestBody AuthorInformationRequest authorInformationRequest
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<AuthorInformationResponse>builder()
                        .message(Messages.MESSAGE_AUTHOR_UPDATED.getMessage())
                        .data(authorAppService.updateAuthorInformation(authorInformationRequest))
                        .build()
        );
    }

    @PutMapping("/image")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update author image")
    public ResponseEntity<GeneralResponse<AuthorInformationResponse>> updateImageAuthor(
            @Valid @RequestBody ImageUploadRequest imageUploadRequest
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<AuthorInformationResponse>builder()
                        .message(Messages.MESSAGE_AUTHOR_IMAGE_UPDATED.getMessage())
                        .data(authorAppService.updateImageAuthor(imageUploadRequest))
                        .build()
        );
    }
    @GetMapping("/{id}")
    @Operation(summary = "Find author by id")
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

    @PostMapping("/files")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Upload author files")
    public ResponseEntity<GeneralResponse<String>> uploadFiles(
            @Valid @RequestBody FileRequest  fileRequest
    ) throws MessagingException, IOException {
        authorAppService.uploadAuthorsByFile(fileRequest);
        return ResponseEntity.ok(
                GeneralResponse.<String>builder()
                        .message(Messages.MESSAGE_AUTHOR_FILES_UPLOADED.getMessage())
                        .data(Messages.MESSAGE_AUTHOR_FILES_UPLOADED.getMessage())
                        .build()
        );
    }
}
