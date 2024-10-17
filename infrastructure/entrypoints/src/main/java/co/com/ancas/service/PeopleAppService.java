package co.com.ancas.service;

import co.com.ancas.request.PeopleRequest;
import co.com.ancas.response.PeopleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PeopleAppService {
    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public PeopleResponse createPeople(PeopleRequest request) {
        return null;
    }
}
