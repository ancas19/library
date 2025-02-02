package co.com.ancas.service;

import co.com.ancas.models.model.*;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.*;
import co.com.ancas.response.BookInformationResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.uses_cases.books.*;
import co.com.ancas.utils.Pagination;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class BooksAppservice {
    private final CreateBookAdapter createBookAdapter;
    private final FindBooksByCriteriaAdapter findBooksByCriteriaAdapter;
    private final FindBookInformationByIdAdapter findBookByIdAdapter;
    private final UpdateImagenBookAdapter updateImagenBookAdapter;
    private final UpdateBookInformationAdapter updateBookInformationAdapter;
    private final ChangeBookStatusAdapter changeBookStatusAdapter;
    private final UploadBooksByFileAdapter uploadBooksFilesAdapter;

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public BookInformationResponse createBook(@Valid BookCreationRequest bookCreationRequest) throws MessagingException, IOException {
        return Mapper.map(createBookAdapter.execute(Mapper.map(bookCreationRequest, BookCreation.class)),BookInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",readOnly = true,rollbackFor = Exception.class)
    public PaginationResponse<BookInformationResponse> findBooksByCriteria(BookSearchCriteriaRequest bookSearchCriteriaRequest,Integer page,Integer size) throws MessagingException, IOException {
        BookSearchCriteria bookSearchCriteria = Mapper.map(bookSearchCriteriaRequest, BookSearchCriteria.class);
        bookSearchCriteria.setPage(page);
        bookSearchCriteria.setSize(size);
        return Pagination.getPaginationResponse(findBooksByCriteriaAdapter.execute(bookSearchCriteria),BookInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",readOnly = true,rollbackFor = Exception.class)
    public BookInformationResponse findBookById(Long id) {
        return Mapper.map(findBookByIdAdapter.execute(id),BookInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public BookInformationResponse updateBookInformation(@Valid BookUpdateRequest bookUpdateRequest) throws MessagingException, IOException {
        return Mapper.map(updateBookInformationAdapter.execute(Mapper.map(bookUpdateRequest, BookUpdate.class)),BookInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public BookInformationResponse updateImageBook(@Valid ImageUploadRequest imageUploadRequest) throws MessagingException, IOException {
        return Mapper.map(updateImagenBookAdapter.execute(Mapper.map(imageUploadRequest, ImageUpload.class)),BookInformationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void changeBookStatus(Long id) throws MessagingException, IOException {
        this.changeBookStatusAdapter.execute(id);
    }

    @Async
    public void uploadBooksFiles(@Valid FileRequest fileRequest) throws MessagingException, IOException {
        this.uploadBooksFilesAdapter.execute(Mapper.map(fileRequest, FileData.class));
    }
}
