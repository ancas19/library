package co.com.ancas.service;

import co.com.ancas.models.model.AuthorInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.request.AuthorCreationRequest;
import co.com.ancas.request.SearchParameterRequest;
import co.com.ancas.response.AuthorInformationResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.uses_cases.authors.*;
import co.com.ancas.utils.RequestMocks;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorAppServiceTest {
    @Mock
    private  CreateAuthorAdapter createAuthorAdapter;
    @Mock
    private  FindAuthorsAdapter findAuthors;
    @Mock
    private  FindAuthorInformationByIdAdapter findAuthorInformationByIdAdapter;
    @Mock
    private  UpdateAuthorImageAdapter updateAuthorImageAdapter;
    @Mock
    private  UpdateAuthorInformationAdapter authorInformationAdapter;
    @Mock
    private  UploadAuthorByFileAdapter uploadAuthorByFileAdapter;
    @InjectMocks
    private AuthorAppService authorAppService;
    private AuthorCreationRequest authorCreationRequest;
    private AuthorInformation authorInformation;
    private SearchParameterRequest searchParameterRequest;

    @BeforeEach
    void setUp() {
        authorInformation = TestMock.authorInformation();
        authorCreationRequest = RequestMocks.authorCreationRequest();
        searchParameterRequest = RequestMocks.searchParameterRequest();
    }

    @Test
    void createAuthor() throws MessagingException, IOException {
        // Arrange
        when(createAuthorAdapter.execute(any())).thenReturn(authorInformation);
        // Act
        AuthorInformationResponse response=authorAppService.createAuthor(authorCreationRequest);
        // Assert
        assertNotNull(response);
    }

    @Test
    void findAuthors() throws MessagingException, IOException {
        // Arrange
        Page<AuthorInformation> page = new PageImpl<>(List.of(authorInformation));
        when(findAuthors.execute(any())).thenReturn(page);
        // Act
        PaginationResponse<AuthorInformationResponse> response= authorAppService.findAuthors(searchParameterRequest,1,1);
        // Assert
        assertNotNull(response);
    }

    @Test
    void findById() throws MessagingException, IOException {
        // Arrange
        when(findAuthorInformationByIdAdapter.execute(any())).thenReturn(authorInformation);
        // Act
        AuthorInformationResponse response= authorAppService.findById(1L);
        // Assert
        assertNotNull(response);
    }

    @Test
    void updateImageAuthor() throws MessagingException, IOException {
        // Arrange
        when(updateAuthorImageAdapter.execute(any())).thenReturn(authorInformation);
        // Act
        AuthorInformationResponse response= authorAppService.updateImageAuthor(RequestMocks.imageUploadRequest());
        // Assert
        assertNotNull(response);
    }

    @Test
    void updateAuthorInformation() throws MessagingException, IOException {
        // Arrange
        when(authorInformationAdapter.execute(any())).thenReturn(authorInformation);
        // Act
        AuthorInformationResponse response= authorAppService.updateAuthorInformation(RequestMocks.authorInformationRequest());
        // Assert
        assertNotNull(response);
    }

    @Test
    void uploadAuthorsByFile() throws MessagingException, IOException {
        // Arrange
        doNothing().when(uploadAuthorByFileAdapter).execute(any());
        //Act
        authorAppService.uploadAuthorsByFile(RequestMocks.fileRequest());
        // Assert
        verify(uploadAuthorByFileAdapter, times(1)).execute(any());
    }
}