package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.*;
import co.com.ancas.response.BookInformationResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.service.BooksAppservice;
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
@Tag(name = "Books")
@RequiredArgsConstructor
@RequestMapping("/v1.0/books")
public class BooksController {
    private final BooksAppservice booksAppservice;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN','EMPLOYEE')")
    @Operation(summary = "Create a book")
    public ResponseEntity<GeneralResponse<BookInformationResponse>> createBook(
            @Valid @RequestBody BookCreationRequest bookCreationRequest
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<BookInformationResponse>builder()
                        .data(this.booksAppservice.createBook(bookCreationRequest))
                        .message(Messages.MESSAGE_BOOK_CREATED.getMessage())
                        .build()
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a book by id")
    public ResponseEntity<GeneralResponse<BookInformationResponse>> findBookById(
            @PathVariable Long id
    )  {
        return ResponseEntity.ok(
                GeneralResponse.<BookInformationResponse>builder()
                        .message(Messages.MESSAGE_BOOK_FOUND.getMessage())
                        .data(this.booksAppservice.findBookById(id))
                        .build()
        );
    }

    @PostMapping("/all")
    @Operation(summary = "Find all books")
    public ResponseEntity<GeneralResponse<PaginationResponse<BookInformationResponse>>> findBooks(
            @Valid @RequestBody BookSearchCriteriaRequest bookSearchCriteriaRequest,
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<PaginationResponse<BookInformationResponse>>builder()
                        .message(Messages.MESSAGE_BOOK_FOUND.getMessage())
                        .data(this.booksAppservice.findBooksByCriteria(bookSearchCriteriaRequest, page, size))
                        .build()
        );
    }

    @PutMapping("/information")
    @PreAuthorize("hasRole('ADMIN','EMPLOYEE')")
    @Operation(summary = "Update book information")
    public ResponseEntity<GeneralResponse<BookInformationResponse>> updateBookInformation(
            @Valid @RequestBody BookUpdateRequest bookUpdateRequest
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<BookInformationResponse>builder()
                        .message(Messages.MESSAGE_BOOK_UPDATED.getMessage())
                        .data(this.booksAppservice.updateBookInformation(bookUpdateRequest))
                        .build()
        );
    }


    @PutMapping("/image")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update book image")
    public ResponseEntity<GeneralResponse<BookInformationResponse>> updateImageBook(
            @Valid @RequestBody ImageUploadRequest imageUploadRequest
            ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<BookInformationResponse>builder()
                        .message(Messages.MESSAGE_BOOK_IMAGE_UPDATED.getMessage())
                        .data(this.booksAppservice.updateImageBook(imageUploadRequest))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Change the status of a book to inactive")
    public ResponseEntity<GeneralResponse<String>> deleteBook(
            @PathVariable Long id
    ) throws MessagingException, IOException {
        this.booksAppservice.changeBookStatus(id);
        return ResponseEntity.ok(
                GeneralResponse.<String>builder()
                        .message(Messages.MESSAGE_CHANGE_BOOK_STATUS.getMessage())
                        .data(Messages.MESSAGE_CHANGE_BOOK_STATUS.getMessage())
                        .build()
        );
    }

    @PostMapping("/files")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Upload book files")
    public ResponseEntity<GeneralResponse<String>> uploadFiles(
            @Valid @RequestBody FileRequest fileRequest
    ) throws MessagingException, IOException {
        this.booksAppservice.uploadBooksFiles(fileRequest);
        return ResponseEntity.ok(
                GeneralResponse.<String>builder()
                        .message(Messages.MESSAGE_BOOK_FILES_UPLOADED.getMessage())
                        .data(Messages.MESSAGE_BOOK_FILES_UPLOADED.getMessage())
                        .build()
        );
    }
}
