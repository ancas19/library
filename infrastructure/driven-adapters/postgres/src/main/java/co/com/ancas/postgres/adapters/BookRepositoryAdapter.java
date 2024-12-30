package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.BookSearchCriteria;
import co.com.ancas.models.repositories.BookRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.BooksEntity;
import co.com.ancas.postgres.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookRepositoryAdapter implements BookRepositoryPort {
    private final BookRepository bookRepository;

    @Override
    public boolean existsByIsbn(String isbn) {
        return this.bookRepository.existsByIsbn(isbn);
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.bookRepository.existsByTitle(title);
    }

    @Override
    public Book save(Book build) {
        return Mapper.map(this.bookRepository.save(Mapper.map(build, BooksEntity.class)), Book.class);
    }

    @Override
    public Page<BookInformation> findBooksByCriteria(BookSearchCriteria bookSearchCriteria) {
        Pageable pageable = PageRequest.of(bookSearchCriteria.getPage(), bookSearchCriteria.getSize());
        return this.bookRepository.findBooksByCriteria(bookSearchCriteria.getSearch().toLowerCase(),bookSearchCriteria.getAuthor(),bookSearchCriteria.getGenre(), pageable);
    }

    @Override
    public Optional<BookInformation> findBookInformationById(Long id) {
        return this.bookRepository.findBookById(id);
    }

    @Override
    public Optional<Book> findById(Long bookId) {
        return this.bookRepository.findById(bookId).map(book -> Mapper.map(book, Book.class));
    }

    @Override
    public boolean existsByIsbnAndIdNot(String isbn, Long id) {
        return this.bookRepository.existsByIsbnAndIdNot(isbn, id);
    }

    @Override
    public boolean existsByTitleAndIdNot(String isbn, Long id) {
        return this.bookRepository.existsByTitleAndIdNot(isbn, id);
    }
}
