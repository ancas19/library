package co.com.ancas.service;

import co.com.ancas.models.model.PeopleCreation;
import co.com.ancas.models.model.PeopleFullInfomration;
import co.com.ancas.models.model.PeopleSearchCriteria;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.PeopleRequest;
import co.com.ancas.request.PeopleSearchCriteriaRequest;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.response.PeopleFullInfomrationResponse;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.uses_cases.people.CreatePersonAdapter;
import co.com.ancas.uses_cases.people.FindPeopleByCriteriaAdapter;
import co.com.ancas.uses_cases.people.FindPeopleByIdAdapter;
import co.com.ancas.utils.Pagination;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PeopleAppService {
    private final CreatePersonAdapter createPersonAdapter;
    private final FindPeopleByCriteriaAdapter findPeopleByCriteriaAdapter;
    private final FindPeopleByIdAdapter findPeopleByIdAdapter;
    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public PeopleResponse createPeople(PeopleRequest request) throws MessagingException {
        return Mapper.map(createPersonAdapter.execute(Mapper.map(request, PeopleCreation.class)), PeopleResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class ,readOnly = true)
    public PaginationResponse<PeopleResponse> findAllByCriteria(PeopleSearchCriteriaRequest request, Pageable pageable) throws MessagingException {
       PeopleSearchCriteria peopleSearchCriteria = Mapper.map(request, PeopleSearchCriteria.class);
       peopleSearchCriteria.setPageable(pageable);
       return Pagination.getPaginationResponse(findPeopleByCriteriaAdapter.execute(peopleSearchCriteria), PeopleResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class ,readOnly = true)
    public PeopleFullInfomrationResponse findById(Long id) throws MessagingException {
        return Mapper.map(findPeopleByIdAdapter.execute(id), PeopleFullInfomrationResponse.class);
    }
}
