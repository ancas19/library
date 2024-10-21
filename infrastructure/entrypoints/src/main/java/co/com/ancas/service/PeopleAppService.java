package co.com.ancas.service;

import co.com.ancas.models.model.PeopleCreation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.PeopleRequest;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.uses_cases.people.CreatePersonAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PeopleAppService {
    private final CreatePersonAdapter createPersonAdapter;
    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public PeopleResponse createPeople(PeopleRequest request) {
        return Mapper.map(createPersonAdapter.execute(Mapper.map(request, PeopleCreation.class)), PeopleResponse.class);
    }
}
