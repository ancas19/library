package co.com.ancas.service;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.LoanCreation;
import co.com.ancas.models.model.LoanInfo;
import co.com.ancas.models.model.LoanSearchByUser;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.LoanInformationResponse;
import co.com.ancas.request.LoanRequest;
import co.com.ancas.request.LoanSearchByUserRequest;
import co.com.ancas.response.LoanDetailsResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.uses_cases.loans.CreateLoanAdapter;
import co.com.ancas.uses_cases.loans.FindLoandByUserAdapter;
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
    private final CurrentUserAppService currentUserAppService;
    private final FindLoandByUserAdapter findLoandByUserAdapter;

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public List<LoanDetailsResponse> createLoan(LoanRequest request) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserDni(request.getDni());
        return Mapper.mapAll(createLoanAdapter.execute(mapLoanRequestToLoanCreation(request)),LoanDetailsResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public PaginationResponse<LoanInformationResponse> findLoansByUser(@Valid LoanSearchByUserRequest request, Integer page, Integer size) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserDni(request.getDni());
        request.setStartDate(startDateNull(request.getStartDate()));
        request.setFinishDate(finishDateNull(request.getFinishDate()));
        verifyDates(request.getStartDate(), request.getFinishDate());
        return Pagination.getPaginationResponse(findLoandByUserAdapter.execute(
                LoanSearchByUser.builder()
                        .dni(request.getDni())
                        .searchBook(request.getSearchBook())
                        .startDate(request.getStartDate())
                        .finishDate(request.getFinishDate())
                        .size(size)
                        .page(page)
                        .build()
        ), LoanInformationResponse.class);
    }

    private void verifyDates(LocalDate startDate, LocalDate finishDate) {
        if (startDate.isAfter(finishDate)) {
            throw new BadRequestException(Messages.MESSAGE_ERROR_DATE_START_AFTER_FINISH.getMessage());
        }
    }

    private LocalDate finishDateNull(LocalDate finishDate) {
        return Objects.isNull(finishDate) ? LocalDate.now() : finishDate;
    }

    private LocalDate startDateNull(LocalDate startDate) {
        return Objects.isNull(startDate) ? LocalDate.now().minusMonths(12) : startDate;
    }


    private LoanCreation mapLoanRequestToLoanCreation(LoanRequest request) {
        return  LoanCreation.builder()
                .dni(request.getDni())
                .details(Mapper.mapAll(request.getLoanInfo(), LoanInfo.class))
                .build();

    }
}
