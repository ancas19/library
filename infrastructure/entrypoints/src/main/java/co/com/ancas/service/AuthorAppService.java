package co.com.ancas.service;

import co.com.ancas.models.model.AuthorCreation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.AuthorCreationRequest;
import co.com.ancas.response.AuthorInformationResponse;
import co.com.ancas.uses_cases.authors.CreateAuthorAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorAppService {
    private final CreateAuthorAdapter createAuthorAdapter;

    @Transactional(value = "libraryTransactionManager",readOnly = true,rollbackFor = Exception.class)
    public AuthorInformationResponse createAuthor(AuthorCreationRequest authorCreationRequest) throws MessagingException, IOException {
        return Mapper.map(createAuthorAdapter.execute(Mapper.map(authorCreationRequest, AuthorCreation.class)),AuthorInformationResponse.class);
    }
}
