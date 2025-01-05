package co.com.ancas.service;

import co.com.ancas.models.model.LoanCreation;
import co.com.ancas.models.model.LoanInfo;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.LoanRequest;
import co.com.ancas.response.LoanDetailsResponse;
import co.com.ancas.uses_cases.loans.CreateLoanAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoanAppService {
    private final CreateLoanAdapter createLoanAdapter;
    private final CurrentUserAppService currentUserAppService;


    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public List<LoanDetailsResponse> createLoan(LoanRequest request) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserDni(request.getDni());
        return Mapper.mapAll(createLoanAdapter.execute(mapLoanRequestToLoanCreation(request)),LoanDetailsResponse.class);
    }

    private LoanCreation mapLoanRequestToLoanCreation(LoanRequest request) {
        return  LoanCreation.builder()
                .dni(request.getDni())
                .details(Mapper.mapAll(request.getLoanInfo(), LoanInfo.class))
                .build();

    }
}
