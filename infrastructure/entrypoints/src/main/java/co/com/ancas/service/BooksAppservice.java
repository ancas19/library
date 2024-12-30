package co.com.ancas.service;

import co.com.ancas.models.model.BookCreation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.BookCreationRequest;
import co.com.ancas.response.BookInformationResponse;
import co.com.ancas.uses_cases.books.CreateBookAdapter;
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

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public BookInformationResponse createBook(@Valid BookCreationRequest bookCreationRequest) throws MessagingException, IOException {
        return Mapper.map(createBookAdapter.execute(Mapper.map(bookCreationRequest, BookCreation.class)),BookInformationResponse.class);
    }
}
