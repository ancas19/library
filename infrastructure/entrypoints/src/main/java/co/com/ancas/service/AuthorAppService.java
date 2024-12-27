package co.com.ancas.service;

import co.com.ancas.models.model.Author;
import co.com.ancas.models.model.AuthorCreation;
import co.com.ancas.models.model.AuthorsSearchCriteria;
import co.com.ancas.models.model.ImageUpload;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.AuthorCreationRequest;
import co.com.ancas.request.AuthorInformationRequest;
import co.com.ancas.request.ImageUploadRequest;
import co.com.ancas.request.SearchParameterRequest;
import co.com.ancas.response.AuthorInformationResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.uses_cases.authors.*;
import co.com.ancas.utils.Pagination;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorAppService {
    private final CreateAuthorAdapter createAuthorAdapter;
    private final FindAuthorsAdapter findAuthors;
    private final FindAuthorInformationByIdAdapter findAuthorInformationByIdAdapter;
    private final UpdateAuthorImageAdapter updateAuthorImageAdapter;
    private final UpdateAuthorInformationAdapter authorInformationAdapter;

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public AuthorInformationResponse createAuthor(AuthorCreationRequest authorCreationRequest) throws MessagingException, IOException {
        return Mapper.map(createAuthorAdapter.execute(Mapper.map(authorCreationRequest, AuthorCreation.class)),AuthorInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",readOnly = true,rollbackFor = Exception.class)
    public PaginationResponse<AuthorInformationResponse> findAuthors(SearchParameterRequest searchParameterRequest,Integer page, Integer size) throws MessagingException, IOException {
        return Pagination.getPaginationResponse(findAuthors.execute(
                AuthorsSearchCriteria.builder()
                        .page(page)
                        .size(size)
                        .search(searchParameterRequest.getSearch())
                        .build()
        ),AuthorInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",readOnly = true,rollbackFor = Exception.class)
    public AuthorInformationResponse findById(Long id) throws MessagingException, IOException {
        return Mapper.map(this.findAuthorInformationByIdAdapter.execute(id),AuthorInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public AuthorInformationResponse updateImageAuthor(ImageUploadRequest imageUploadRequest) throws MessagingException, IOException {
        return  Mapper.map(this.updateAuthorImageAdapter.execute(Mapper.map(imageUploadRequest, ImageUpload.class)),AuthorInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public AuthorInformationResponse updateAuthorInformation(AuthorInformationRequest authorInformationRequest) throws MessagingException, IOException {
        return Mapper.map(this.authorInformationAdapter.execute(Mapper.map(authorInformationRequest, Author.class)),AuthorInformationResponse.class);
    }
}
