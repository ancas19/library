package co.com.ancas.utils;

import co.com.ancas.request.*;

import java.time.LocalDate;
import java.util.List;

public class RequestMocks {
    private RequestMocks() {}
    public  static ChangePasswordRequest changePasswordRequest(){
        return ChangePasswordRequest.builder()
                .username("USERNAME.PEPE1")
                .password("Password123@")
                .confirmPassword("Password123@")
                .build();
    }

    public static PeopleRequest peopleRequest(){
        return PeopleRequest.builder()
                .dni("12345678")
                .firstName("JOHN")
                .lastName("DOE")
                .email("john.doe@example.com")
                .phone("+1234567890")
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
                .firstName("JOHN")
                .lastName("DOE")
                .phone("+1234567890")
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
                .password("Password123@")
                .passwordRepeat("Password123@")
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
                .username("USERNAME.TODOS1")
                .password("Password123@")
                .build();
    }

    public static AuthorCreationRequest authorCreationRequest() {
        return AuthorCreationRequest.builder()
                .fullName("GABRIEL GARCIA MARQUEZ")
                .nationality("COLOMBIA")
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
                .fullName("TOMAS CARRASQUILLA")
                .nationality("COLOMBIA")
                .birthdate(LocalDate.of(1927, 3, 6))
                .bio("Renowned author of magical realism.")
                .build();
    }

    public static FileRequest fileRequest() {
        return FileRequest.builder()
                .nameFile("authors.csv")
                .base64("base64EncodedStringHere")
                .extension("csv")
                .build();
    }

    public static  BookCreationRequest bookCreationRequest(){
        return BookCreationRequest.builder()
                .title("ALICES ADVENTURES IN WONDERLAND")
                .isbn("9783161484100")
                .publishDate(LocalDate.of(1967, 5, 30))
                .author("PETER PAN")
                .genre("FAIRYTALE")
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
                .genre("ROMANTIC")
                .author("GA")
                .build();
    }

    public static BookUpdateRequest bookUpdateRequest(){
        return BookUpdateRequest.builder()
                .id(1L)
                .title("PRINCIPE")
                .isbn("9783161484100")
                .publishDate(LocalDate.of(1967, 5, 30))
                .author("TOMAS CARRASQUILLA")
                .genre("ROMANTIC")
                .availableCopies(10)
                .available("YES")
                .blurb("A masterpiece of Latin American literature.")
                .build();
    }

    public static DniRequest dniRequest() {
        return DniRequest.builder()
                .dni("12345678")
                .build();
    }
}
