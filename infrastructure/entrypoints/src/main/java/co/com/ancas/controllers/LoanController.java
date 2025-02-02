package co.com.ancas.controllers;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.request.*;
import co.com.ancas.response.*;
import co.com.ancas.service.LoanAppService;
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
import java.util.List;

@Validated
@RestController
@Tag(name = "Loans")
@RequiredArgsConstructor
@RequestMapping("/v1.0/loans")
public class LoanController {

    private final LoanAppService loanAppService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN','EMPLOYEE')")
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

    @PostMapping("/users")
    @Operation(summary = "Find loans by user")
    public ResponseEntity<GeneralResponse<PaginationResponse<LoanInformationResponse>>> findLoansByUser(
            @RequestBody @Valid LoanSearchByUserRequest request,
            @RequestParam(defaultValue = "0", required = false, name = "page") Integer page,
            @RequestParam(defaultValue = "10", required = false, name = "size") Integer size
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<PaginationResponse<LoanInformationResponse>>builder()
                        .data(loanAppService.findLoansByUser(request, page, size))
                        .message(Messages.MESSAGE_LOANS_FOUND.getMessage())
                        .build()
        );
    }

    @PostMapping("/fee")
    @Operation(summary = "Calculate value to pay")
    public ResponseEntity<GeneralResponse<List<LoanReturnValueResponse>>> calculateValueToPay(
        @Valid @RequestBody DniRequest dniRequest
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<List<LoanReturnValueResponse>>builder()
                        .data(loanAppService.calculateValueToPay(dniRequest.getDni()))
                        .message(Messages.MESSAGE_VALUE_TO_PAY_CALCULATED.getMessage())
                        .build()
        );
    }

    @PatchMapping("/checkout-completion")
    @PreAuthorize("hasRole('ADMIN','EMPLOYEE')")
    @Operation(summary = "Return loan")
    public ResponseEntity<GeneralResponse<LoanReturnResultResponse>> returnLoan(
           @Valid @RequestBody List<LoanReturnRequest> loanReturnRequests
    ) throws MessagingException, IOException {
        return ResponseEntity.ok(
                GeneralResponse.<LoanReturnResultResponse>builder()
                        .data(loanAppService.returnLoan(loanReturnRequests))
                        .message(Messages.MESSAGE_LOAN_RETURNED.getMessage())
                        .build()
        );
    }
}
