package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.Book;
import co.com.ancas.models.model.BookInformation;
import co.com.ancas.models.model.BookSearchCriteria;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.postgres.entities.BooksEntity;
import co.com.ancas.postgres.repositories.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookRepositoryAdapterTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookRepositoryAdapter bookRepositoryAdapter;
    private BooksEntity bookEntity;
    private Book book;
    private BookSearchCriteria bookSearchCriteria;
    private BookInformation bookInformation;

    @BeforeEach
    void setUp(){
        bookInformation = TestMock.bookInformation();
        book= TestMock.book();
        bookEntity = Mapper.map(book, BooksEntity.class);
        bookSearchCriteria = TestMock.bookSearchCriteria();
    }

    @Test
    void save(){
        //Arrange
        when(bookRepository.save(any())).thenReturn(bookEntity);
        //Act
        Book bookCreated=bookRepositoryAdapter.save(book);
        //Assert
        assertNotNull(bookCreated);
    }

    @Test
    void existByIsbn(){
        //Arrange
        when(bookRepository.existsByIsbn(any())).thenReturn(true);
        //Act
        boolean exist=bookRepositoryAdapter.existsByIsbn("isbn");
        //Assert
        assertTrue(exist);
    }

    @Test
    void existsByTitle(){
        //Arrange
        when(bookRepository.existsByTitle(any())).thenReturn(true);
        //Act
        boolean exist=bookRepositoryAdapter.existsByTitle("title");
        //Assert
        assertTrue(exist);
    }

    @Test
    void findBooksByCriteria(){
        //Arrange
        Page<BookInformation> bookInformationPage = new PageImpl<>(List.of(bookInformation));
        when(bookRepository.findBooksByCriteria(any(),any(),any(),any())).thenReturn(bookInformationPage);
        //Act
        Page<BookInformation> books=bookRepositoryAdapter.findBooksByCriteria(bookSearchCriteria);
        //Assert
        assertNotNull(books);
    }

    @Test
    void findBookInformationById(){
        //Arrange
        when(bookRepository.findBookById(1L)).thenReturn(java.util.Optional.of(bookInformation));
        //Act
        java.util.Optional<BookInformation> bookResponse = bookRepositoryAdapter.findBookInformationById(1L);
        //Assert
        assertNotNull(bookResponse);
    }

    @Test
    void findById(){
        //Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(bookEntity));
        //Act
        Optional<Book> bookResponse = bookRepositoryAdapter.findById(1L);
        //Assert
        assertNotNull(bookResponse);
    }

    @Test
    void existsByIsbnAndIdNot(){
        //Arrange
        when(bookRepository.existsByIsbnAndIdNot(any(),any())).thenReturn(true);
        //Act
        boolean exists = bookRepositoryAdapter.existsByIsbnAndIdNot("isbn", 1L);
        //Assert
        assertTrue(exists);
    }

    @Test
    void existsByTitleAndIdNot(){
        //Arrange
        when(bookRepository.existsByTitleAndIdNot(any(),any())).thenReturn(true);
        //Act
        boolean exists = bookRepositoryAdapter.existsByTitleAndIdNot("title", 1L);
        //Assert
        assertTrue(exists);
    }

    @Test
    void findBookByIsbn(){
        //Arrange
        when(bookRepository.findByIsbn("isbn")).thenReturn(Optional.of(bookEntity));
        //Act
        Optional<Book> bookResponse = bookRepositoryAdapter.findBookByIsbn("isbn");
        //Assert
        assertNotNull(bookResponse);
    }
}