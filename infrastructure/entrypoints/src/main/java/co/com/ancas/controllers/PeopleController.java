package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.model.PeopleSearchCriteria;
import co.com.ancas.request.PeopleRequest;
import co.com.ancas.request.PeopleSearchCriteriaRequest;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "People")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/people")
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


    @PostMapping("/find-all")
    @Operation(summary = "Find all People", description = "Endpoint to find all People")
    public ResponseEntity<GeneralResponse<PaginationResponse<PeopleResponse>>> findAll(
            @Valid @RequestBody PeopleSearchCriteriaRequest request,
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size
    ) throws MessagingException {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        GeneralResponse.<PaginationResponse<PeopleResponse>>builder()
                                .message("People found successfully")
                                .data(peopleService.findAllByCriteria(request, pageable))
                                .build()
                );
    }

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
}
