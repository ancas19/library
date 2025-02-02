package co.com.ancas.models.utils;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.model.*;

import java.time.LocalDate;

public class TestMock {
     private TestMock () {
     }


     public static AuthLogin authLogin(){
         return AuthLogin.builder()
                 .password("uwijbskow2")
                 .username("anasdla")
                 .build();
     }


     public static PeopleFullInfomration peopleFullInfomration(){
         return PeopleFullInfomration.builder()
                 .dni("123456789")
                 .firstName("John")
                 .lastName("Doe")
                 .email("john.doe@example.com")
                 .phone("123-456-7890")
                 .role("Admin")
                 .membership("Premium")
                 .profileImage("image.jpg")
                 .username("johndoe")
                 .status("Active")
                 .build();
     }

     public static Attempt attempt(){
         return Attempt.builder()
                 .username("username")
                 .quantity(3)
                 .build();
     }

     public static AuthorCreation authorCreation(){
         return AuthorCreation.builder()
                 .id(1L)
                 .fullName("Gabriel Márquez")
                 .nationality("Colombian")
                 .birthdate(LocalDate.of(1927, 3, 6))
                 .bio("Colombian dsdsdsdsdsd, short-story writer, and journalist, known for 'One Hundred Years of Solitude'.")
                 .nameFile("profile.jpg")
                 .base64("base64EncodedStringHere")
                 .build();
     }

     public static Image image(){
         return Image.builder()
                 .id(1L)
                 .fileName("picture.jpg")
                 .filePath("/images/picture.jpg")
                 .idServer("abc123xyz")
                 .build();
     }

     public static Author author(){
         return  Author.builder()
                 .id(1L)
                 .fullName("Gabriel Perez Márquez")
                 .nationality("Venezuelan")
                 .birthdate(LocalDate.of(1927, 3, 6))
                 .bio(" known for 'One Hundred Years of Solitude'.")
                 .imageId(101L)  // Reference to an image stored elsewhere
                 .build();
     }

    public static AuthorInformation authorInformation() {
        return AuthorInformation.builder()
                .id(1L)
                .fullName("Pwpw Márquez")
                .nationality("Colombian")
                .birthdate(LocalDate.of(1927, 3, 6))
                .bio("Colombian novelist")
                .image("https://example.com/images/gabo.jpg") // URL or base64 string
                .build();
     }

     public static  AuthorsSearchCriteria authorsSearchCriteria(){
         return AuthorsSearchCriteria.builder()
                 .search("Martes García Márquez")
                 .page(1)
                 .size(10)
                 .build();
     }

     public static ImageUpload imageUpload(){
         return ImageUpload.builder()
                 .nameFile("peep.jpg")
                 .base64("base64EncodedStringHere")
                 .idImage(1L)
                 .id(1L)
                 .build();
     }

     public static Book book(){
         return Book.builder()
                 .id(1L)
                 .title("One of Solitude")
                 .isbn("978-0-307-27789-1")
                 .authorId(1L) // Reference to an author stored elsewhere
                 .genreId(1l)
                 .publishDate(LocalDate.of(1967, 5, 30))
                 .available("YES")
                 .availableCopies(5)
                 .imageId(101L)
                    .blurb("A novel by Colombian author Gabriel García Márquez")
                 .build();
     }

     public static AvailableCopiesUpdate availableCopiesUpdate(){
         return AvailableCopiesUpdate.builder()
                 .bookId(1L)
                 .copies(2)
                 .action(Constants.DISCOUNT)
                 .build();
     }

     public static BookCreation bookCreation(){
         return BookCreation.builder()
                 .title("Alice's Adventures in Wonderland")
                 .isbn("978-0-787878787-27789-1")
                 .author("Lewis Carroll")
                 .genre("Mistery")
                 .publishDate(LocalDate.of(1967, 5, 30))
                 .available("YES")
                 .availableCopies(5)
                 .blurb("A novel by English author Lewis Carroll")
                 .nameFile("cover.jpg")
                 .base64("base64EncodedStringHere")
                 .build();
     }

     public static Genre genre(){
         return Genre.builder()
                 .id(1L)
                 .value("Fantasy")
                 .build();
     }

    public static BookInformation bookInformation() {
         return BookInformation.builder()
                 .id(1L)
                 .title("Litte prince")
                 .isbn("978-7878787878-307-27789-1")
                 .author("Gabriel García Márquez")
                 .genre("Humor")
                 .publishDate(LocalDate.of(1967, 5, 30))
                 .availableCopies(5)
                 .blurb("A novel by British author Lewis Carroll")
                 .bookImage("https://example.com/images/one-hundred-years.jpg") // URL or base64 string
                 .build();
    }

    public static BookSearchCriteria bookSearchCriteria() {
            return BookSearchCriteria.builder()
                    .search("One Hundred Years of Solitude")
                    .page(1)
                    .size(10)
                    .build();
     }

    public static BookUpdate bookUpdate() {
        return BookUpdate.builder()
                .id(1L)
                .title("The Alchemist")
                .isbn("978-44333688-307-27789-1")
                .author("Paulo Coelho")
                .genre("Fantasy")
                .publishDate(LocalDate.of(1967, 5, 30))
                .availableCopies(5)
                .blurb("A novel by Colombian author Paul Coelho")
                .build();
     }

    public static TokenInformation tokrnInformation() {
        return TokenInformation.builder()
                .token("TOKEN")
                .username("USERNAME")
                .email("PEPEQUINTE@GMAIL.COM")
                .dni("12121212")
                .role("ROLES")
                .idPersona(1L)
                .idUser(2L)
                .build();
     }

    public static Membership membership() {
        return Membership.builder()
                .id(1L)
                .membershipType("MEMBERSHIP")
                .loanLimit(5)
                .loanPeriodDays(23)
                .gracePeriodDays(2)
                .finePerDay(3.0)
                .build();
    }

    public static UserCreation userCreation() {
        return UserCreation.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john.doe@example.com")
                .userType("ADMIN")
                .build();
    }

    public static User user() {
        return User.builder()
                .id(1L)
                .personId(1L)
                .username("johndoe")
                .password("password")
                .roleId(1L)
                .membershipId(1L)
                .emailVerified(false)
                .changePassword(true)
                .build();
    }


    public static  TokenInformation tokenInformation() {
        return TokenInformation.builder()
                .token("TOKEN")
                .username("USERNAME")
                .email("PEPEQUINTE@GMAIL.COM")
                .dni("12121212")
                .role("ROLES")
                .idPersona(1L)
                .idUser(2L)
                .build();
    }

    public static UserMembershipInfo userMembershipInfo() {
        return UserMembershipInfo.builder()
                .userId(1L)
                .username("johndoe")
                .email("johndoe@example.com")
                .membershipType("Premium")
                .loanLimit(5)
                .loanPeriodDays(14)
                .gracePeriodDays(3)
                .dailyFine(1.50)
                .build();
     }

    public static UserInformation userInformation() {
        return UserInformation.builder()
                .id(1L)
                .username("johndoe")
                .role("ADMIN")
                .membership("Premium")
                .emailVerified(true)
                .changePassword(false)
                .build();
     }

    public static Code code() {
        return Code.builder()
                .email("PEPEQINTE@GMAIL.COM")
                .code("123456")
                .build();
     }

    public static PasswordRecovery passwordRecovery() {
        return PasswordRecovery.builder()
                .email("PEPEQINTE@GMAIL.COM")
                .code("123456")
                .password("password")
                .passwordRepeat("password")
                .build();
     }

    public static UpdatePassword updatePassword() {
        return UpdatePassword.builder()
                .username("johndoe")
                .password("password")
                .confirmPassword("password")
                .build();
     }

    public static People people() {
        return  People.builder()
                .id(1L)
                .dni("12345678")
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phone("+1234567890")
                .status("ACTIVE")
                .profileImage(101L)
                .build();
     }

    public static PeopleCreation peopleCreation() {
       return PeopleCreation.builder()
               .dni("12345678A")
               .firstName("John")
               .lastName("Doe")
               .email("john.doe@example.com")
               .phone("123-456-7890")
               .userType("Admin")
               .status("Active")
               .build();
     }

    public static PeopleSearchCriteria peopleSearchCriteria() {
        return PeopleSearchCriteria.builder()
                .search("John Doe")
                .page(1)
                .size(10)
                .build();
     }

    public static PersonAccess personAccess() {
        return PersonAccess.builder()
                .email("pepe@email.com")
                .build();
    }

    public static PersonCode personCode() {
        return PersonCode.builder()
                .email("person@email.com")
                .code("123456")
                .build();
     }
}
