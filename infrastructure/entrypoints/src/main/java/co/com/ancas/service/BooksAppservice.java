package co.com.ancas.service;

import co.com.ancas.models.model.BookCreation;
import co.com.ancas.models.model.BookSearchCriteria;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.BookCreationRequest;
import co.com.ancas.request.BookSearchCriteriaRequest;
import co.com.ancas.response.BookInformationResponse;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.uses_cases.books.CreateBookAdapter;
import co.com.ancas.uses_cases.books.FindBookInformationByIdAdapter;
import co.com.ancas.uses_cases.books.FindBooksByCriteriaAdapter;
import co.com.ancas.utils.Pagination;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class BooksAppservice {
    private final CreateBookAdapter createBookAdapter;
    private final FindBooksByCriteriaAdapter findBooksByCriteriaAdapter;
    private final FindBookInformationByIdAdapter findBookByIdAdapter;

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
    public BookInformationResponse findBookById(Long id) throws MessagingException, IOException {
        return Mapper.map(findBookByIdAdapter.execute(id),BookInformationResponse.class);
    }
}
