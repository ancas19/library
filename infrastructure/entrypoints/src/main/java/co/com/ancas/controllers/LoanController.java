package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.LoanRequest;
import co.com.ancas.response.GeneralResponse;
import co.com.ancas.response.LoanDetailsResponse;
import co.com.ancas.service.LoanAppService;
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
import java.util.List;

@Validated
@RestController
@Tag(name = "Loans")
@RequiredArgsConstructor
@RequestMapping("/v1.0/loans")
public class LoanController {

    private final LoanAppService loanAppService;

    @PostMapping
    @Operation(summary = "Create a loan")
    public ResponseEntity<GeneralResponse<List<LoanDetailsResponse>>> createLoan(
            @RequestBody @Valid LoanRequest request
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<List<LoanDetailsResponse>>builder()
                        .data(loanAppService.createLoan(request))
                        .message(Messages.MESSAGE_LOAN_CREATED.getMessage())
                        .build()
        );
    }
}
