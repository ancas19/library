package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.model.PeopleSearchCriteria;
import co.com.ancas.request.*;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.response.PeopleFullInfomrationResponse;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.service.PeopleAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static co.com.ancas.models.enums.Messages.*;

@Tag(name = "People")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/people")
@Validated
public class PeopleController {
    private final PeopleAppService peopleService;

    @PostMapping("/all")
    @Operation(summary = "Find all People", description = "Endpoint to find all People")
    public ResponseEntity<GeneralResponse<PaginationResponse<PeopleResponse>>> findAll(
            @Valid @RequestBody PeopleSearchCriteriaRequest request,
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size
    ) throws MessagingException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<PaginationResponse<PeopleResponse>>builder()
                                .message(MESSAGE_PEOPLE_FOUND.getMessage())
                                .data(peopleService.findAllByCriteria(request, page, size))
                                .build()
                );
    }
    //TODO: Determinate rolwe to update all people or only myself
    @GetMapping("/{id}")
    @Operation(summary = "Find People by id", description = "Endpoint to find People by id")
    public ResponseEntity<GeneralResponse<PeopleFullInfomrationResponse>> findById(
            @PathVariable Long id
    ) throws MessagingException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<PeopleFullInfomrationResponse>builder()
                                .message(Messages.MESSAGE_PEOPLE_FULL_INFOMRATION.getMessage())
                                .data(peopleService.findById(id))
                                .build()
                );
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PatchMapping
    @Operation(summary = "Update People", description = "Endpoint to update People")
    public ResponseEntity<GeneralResponse<PeopleResponse>> updatePeople(
            @Valid @RequestBody PeopleInformationRequest request
    ) throws MessagingException, IOException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<PeopleResponse>builder()
                                .message(MESSAGE_PEOPLE_UPDATED.getMessage())
                                .data(peopleService.updatePeople(request))
                                .build()
                );
    }

    @PostMapping("/profile-image")
    @Operation(summary = "Upload profile image", description = "Endpoint to upload profile image")
    public ResponseEntity<GeneralResponse<String>> uploadProfileImage(
            @Valid @RequestBody ImageUploadRequest imageUpload
    ) throws MessagingException, IOException {
        peopleService.uploadProfileImage(imageUpload);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<String>builder()
                                .message(MESSAGE_IMAGE_UPLOAD.getMessage())
                                .data(MESSAGE_IMAGE_UPLOAD.getMessage())
                                .build()
                );
    }



    @DeleteMapping("/status/{id}")
    @Operation(summary = "Block a person", description = "Endpoint to delete block a person")
    public ResponseEntity<GeneralResponse<String>> blockPeople(@PathVariable Long id) throws MessagingException, IOException {
        peopleService.blockPeople(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<String>builder()
                                .message(MESSAGE_PEOPLE_BLOCKED.getMessage())
                                .data(MESSAGE_PEOPLE_BLOCKED.getMessage())
                                .build()
                );
    }
}
