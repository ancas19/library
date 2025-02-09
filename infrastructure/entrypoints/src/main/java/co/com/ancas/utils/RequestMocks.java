package co.com.ancas.utils;

import co.com.ancas.request.*;

import java.time.LocalDate;
import java.util.List;

public class RequestMocks {
    private RequestMocks() {}
    public  static ChangePasswordRequest changePasswordRequest(){
        return ChangePasswordRequest.builder()
                .username("username")
                .password("password")
                .confirmPassword("password")
                .build();
    }

    public static PeopleRequest peopleRequest(){
        return PeopleRequest.builder()
                .dni("12345678")
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .phone("1234567890")
                .userType("ADMIN")
                .build();
    }

    public static PeopleSearchCriteriaRequest peopleSearchCriteriaRequest(){
        return PeopleSearchCriteriaRequest.builder()
                .search("search")
                .role("ADMIN")
                .build();
    }

    public static ImageUploadRequest imageUploadRequest() {
        return ImageUploadRequest.builder()
                .nameFile("peep.jpg")
                .base64("base64EncodedStringHere")
                .id(1L)
                .build();
    }

    public static PeopleInformationRequest peopleInformationRequest(){
        return PeopleInformationRequest.builder()
                .id(1L)
                .dni("12345678")
                .firstName("John")
                .lastName("Doe")
                .phone("1234567890")
                .build();
    }

    public static PersonAccessRequest personAccessRequest() {
        return PersonAccessRequest.builder()
                .email("email@email.com")
                .build();
    }

    public static PersonCodeRequest personCodeRequest() {
        return PersonCodeRequest.builder()
                .code("123456")
                .email("email@email.com")
                .build();
    }

    public static PasswordRecoveryRequest passwordRecoveryRequest() {
        return PasswordRecoveryRequest.builder()
                .email("email@email.com")
                .code("123456")
                .password("password")
                .passwordRepeat("password")
                .build();
    }

    public static LoanRequest loanRequest() {
        return LoanRequest.builder()
                .dni("12345678")
                .loanInfo(
                        List.of(
                                LoanInfoRequest.builder()
                                        .isbn("2323232323")
                                        .quantity(1)
                                        .build()
                        )
                )
                .build();
    }

    public static LoanReturnRequest loanReturnRequest() {
        return LoanReturnRequest.builder()
                .idLoan(1L)
                .fine(200.0)
                .comment("Comment")
                .build();
    }

    public static LoanSearchByUserRequest loanSearchByUserRequest() {
        return LoanSearchByUserRequest.builder()
                .dni("12345678")
                .searchBook("2323232323")
                .startDate(LocalDate.now())
                .finishDate(LocalDate.now())
                .typeSearch("HISTORICAL")
                .build();
    }

    public static AuthLoginRequest authLoginRequest() {
        return AuthLoginRequest.builder()
                .username("username")
                .password("password")
                .build();
    }

    public static AuthorCreationRequest authorCreationRequest() {
        return AuthorCreationRequest.builder()
                .fullName("Gabriel García Márquez")
                .nationality("Colombian")
                .birthdate(LocalDate.of(1927, 3, 6))
                .bio("Renowned author of magical realism.")
                .nameFile("profile_picture.jpg")
                .base64("base64EncodedStringHere")
                .build();
    }

    public static SearchParameterRequest searchParameterRequest() {
        return SearchParameterRequest.builder()
                .search("search")
                .build();
    }

    public static AuthorInformationRequest authorInformationRequest() {
        return AuthorInformationRequest.builder()
                .id(1L)
                .fullName("Gabriel García Márquez")
                .nationality("Colombian")
                .birthdate(LocalDate.of(1927, 3, 6))
                .build();
    }

    public static FileRequest fileRequest() {
        return FileRequest.builder()
                .nameFile("authors.csv")
                .base64("base64EncodedStringHere")
                .build();
    }

    public static  BookCreationRequest bookCreationRequest(){
        return BookCreationRequest.builder()
                .title("One Hundred Years of Solitude")
                .isbn("978-3-16-148410-0")
                .publishDate(LocalDate.of(1967, 5, 30))
                .author("Gabriel García Márquez")
                .genre("Magical Realism")
                .availableCopies(10)
                .available("YES")
                .blurb("A masterpiece of Latin American literature.")
                .nameFile("book_cover.jpg")
                .base64("base64EncodedImageHere")
                .build();
    }

    public static BookSearchCriteriaRequest bookSearchCriteriaRequest(){
        return BookSearchCriteriaRequest.builder()
                .search("search")
                .genre("Magical Realism")
                .author("Gabriel García Márquez")
                .build();
    }

    public static BookUpdateRequest bookUpdateRequest(){
        return BookUpdateRequest.builder()
                .id(1L)
                .title("One Hundred Years of Solitude")
                .isbn("978-3-16-148410-0")
                .publishDate(LocalDate.of(1967, 5, 30))
                .author("Gabriel García Márquez")
                .genre("Magical Realism")
                .availableCopies(10)
                .available("YES")
                .blurb("A masterpiece of Latin American literature.")
                .build();
    }
}
