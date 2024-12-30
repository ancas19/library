package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.BookCreationRequest;
import co.com.ancas.response.BookInformationResponse;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.service.BooksAppservice;
import io.swagger.v3.oas.annotations.Operation;
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
@Tag(name = "Books")
@RequiredArgsConstructor
@RequestMapping("/v1.0/books")
public class BooksController {
    private final BooksAppservice booksAppservice;

    @PostMapping
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
}
