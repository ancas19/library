package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.service.GenresAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@Tag(name = "Genres")
@RequiredArgsConstructor
@RequestMapping("/v1.0/genres")
public class GenresController {

    private final GenresAppService genresAppService;

    @GetMapping
    @Operation(summary = "Get all genres")
    public ResponseEntity<GeneralResponse<List<String>>> findAll() {
        return ResponseEntity.ok(
                GeneralResponse.<List<String>>builder()
                        .data(genresAppService.findAll())
                        .message(Messages.MESSAGE_GENRES_FOUND.getMessage())
                        .build()
        );
    }
}
