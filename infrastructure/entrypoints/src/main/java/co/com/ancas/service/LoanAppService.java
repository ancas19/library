package co.com.ancas.service;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.enums.TypeSearch;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.*;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.response.LoanInformationResponse;
import co.com.ancas.request.LoanRequest;
import co.com.ancas.request.LoanReturnRequest;
import co.com.ancas.request.LoanSearchByUserRequest;
import co.com.ancas.response.*;
import co.com.ancas.uses_cases.loans.CalculateValueToPayAdapter;
import co.com.ancas.uses_cases.loans.CreateLoanAdapter;
import co.com.ancas.uses_cases.loans.FindLoandByUserAdapter;
import co.com.ancas.uses_cases.loans.ReturnLoanAdapter;
import co.com.ancas.utils.Pagination;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LoanAppService {
    private final CreateLoanAdapter createLoanAdapter;
    private final ReturnLoanAdapter returnLoanAdapter;
    private final CurrentUserAppService currentUserAppService;
    private final FindLoandByUserAdapter findLoandByUserAdapter;
    private final CalculateValueToPayAdapter calculateValueToPayAdapter;

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public List<LoanDetailsResponse> createLoan(LoanRequest request) throws MessagingException, IOException {
        return Mapper.mapAll(createLoanAdapter.execute(mapLoanRequestToLoanCreation(request)),LoanDetailsResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public List<LoanReturnValueResponse> calculateValueToPay(String dni) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserDniAndRole(dni);
        return Mapper.mapAll(calculateValueToPayAdapter.execute(dni), LoanReturnValueResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public LoanReturnResultResponse returnLoan(List<LoanReturnRequest> loanReturnRequests) throws MessagingException, IOException {
        return  Mapper.map(returnLoanAdapter.execute(Mapper.mapAll(loanReturnRequests, LoanReturn.class)),LoanReturnResultResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public PaginationResponse<LoanInformationResponse> findLoansByUser( LoanSearchByUserRequest request, Integer page, Integer size) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserDniAndRole(request.getDni());
        request.setStartDate(startDateNull(request.getStartDate()));
        request.setFinishDate(finishDateNull(request.getFinishDate()));
        verifyDates(request.getStartDate(), request.getFinishDate());
        return Pagination.getPaginationResponse(findLoandByUserAdapter.execute(
                LoanSearchByUser.builder()
                        .dni(request.getDni())
                        .searchBook(request.getSearchBook())
                        .startDate(request.getStartDate())
                        .finishDate(request.getFinishDate())
                        .typeSearch(TypeSearch.valueOf(request.getTypeSearch().toUpperCase()))
                        .size(size)
                        .page(page)
                        .build()
        ), LoanInformationResponse.class);
    }

    private void verifyDates(LocalDate startDate, LocalDate finishDate) {
        if (startDate.isBefore(finishDate)) {
            throw new BadRequestException(Messages.MESSAGE_ERROR_DATE_START_AFTER_FINISH.getMessage());
        }
    }

    private LocalDate finishDateNull(LocalDate finishDate) {
        return Objects.isNull(finishDate) ? LocalDate.now().minusMonths(1) : finishDate;
    }

    private LocalDate startDateNull(LocalDate startDate) {
        return Objects.isNull(startDate) ? LocalDate.now(): startDate;
    }


    private LoanCreation mapLoanRequestToLoanCreation(LoanRequest request) {
        return  LoanCreation.builder()
                .dni(request.getDni())
                .details(Mapper.mapAll(request.getLoanInfo(), LoanInfo.class))
                .build();

    }
}
