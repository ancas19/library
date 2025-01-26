package co.com.ancas.models.model;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.utils.Constants;
import jakarta.annotation.PostConstruct;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

import static co.com.ancas.models.enums.Constants.NO;
import static co.com.ancas.models.enums.Constants.YES;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookCreation {
    private String title;
    private String isbn;
    private String author;
    private LocalDate publishDate;
    private String genre;
    private Integer availableCopies;
    private String blurb;
    private String available;
    private String nameFile;
    private String base64;


    public void validate(){
            if (Objects.isNull(title) || title.isEmpty()) {
                throw new BadRequestException(Constants.TITLE_REQUIRED);
            }
            if (!title.matches(Constants.LETTERS_AND_NUMBERS)) {
                throw new BadRequestException(Constants.TITLE_INVALID);
            }

            if (Objects.isNull(isbn) || isbn.isEmpty()) {
                throw new BadRequestException(Constants.ISBN_REQUIRED);
            }
            if (!isbn.matches(Constants.ISBN)) {
                throw new BadRequestException(Constants.ISBN_INVALID);
            }

            if (Objects.isNull(author) || author.isEmpty()) {
                throw new BadRequestException(Constants.AUTHOR_REQUIRED);
            }
            if (!author.matches(Constants.LETTERS)) {
                throw new BadRequestException(Constants.AUTHOR_INVALID);
            }

            if (Objects.isNull(publishDate)) {
                throw new BadRequestException(Constants.PUBLISH_DATE_REQUIRED);
            }
            if (publishDate.isAfter(LocalDate.now())) {
                throw new BadRequestException(Constants.PUBLISH_DATE_INVALID);
            }

            if (Objects.isNull(genre) || genre.isEmpty()) {
                throw new BadRequestException(Constants.GENRE_REQUIRED);
            }
            if (!genre.matches(Constants.LETTERS)) {
                throw new BadRequestException(Constants.GENRE_INVALID);
            }

            if (Objects.isNull(availableCopies) || availableCopies < 0) {
                throw new BadRequestException(Constants.AVAILABLE_COPIES_INVALID);
            }

            if (Objects.isNull(available) || available.isEmpty()) {
                    throw new BadRequestException(Constants.AVAILABLE_REQUIRED);
            }
            if (!available.matches("%s|%s".formatted(YES, NO))) {
                throw new BadRequestException(Constants.AVAILABLE_INVALID);
            }

            if (Objects.isNull(blurb) || blurb.isEmpty()) {
                throw new BadRequestException(Constants.BLURB_REQUIRED);
            }
            if (!blurb.matches(Constants.LETTERS_AND_SPECIAL_CHARACTERS)) {
                throw new BadRequestException(Constants.BLURB_INVALID);
            }

            if (Objects.isNull(nameFile) || nameFile.isEmpty()) {
                throw new BadRequestException(Constants.NAME_FILE_REQUIRED);
            }
            if (Objects.isNull(base64) || base64.isEmpty()) {
                throw new BadRequestException(Constants.BASE64_REQUIRED);
            }

    }

    public  static BookCreation bookFromFile(String title, String isbn, String author, LocalDate publishDate, String genre, Integer availableCopies, String blurb, String available, String nameFile, String base64){
        BookCreation book = BookCreation.builder()
                .title(title)
                .isbn(isbn)
                .author(author)
                .publishDate(publishDate)
                .genre(genre)
                .availableCopies(availableCopies)
                .blurb(blurb)
                .available(available)
                .nameFile(nameFile)
                .base64(base64)
                .build();
        book.validate();
        return book;
    }
}
