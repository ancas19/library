package co.com.ancas.models.utils;

import co.com.ancas.models.model.*;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.ArrayList;

public class TestMock {
     private TestMock () {
     }

     public static AuthLogin authLogin(){
         return AuthLogin.builder()
                 .password("password")
                 .username("username")
                 .build();
     }

     public static User user(){
         return new User("username", "password", new ArrayList<>());
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
                 .profileImage("profile.jpg")
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
                 .fullName("Gabriel García Márquez")
                 .nationality("Colombian")
                 .birthdate(LocalDate.of(1927, 3, 6))
                 .bio("Colombian novelist, short-story writer, and journalist, known for 'One Hundred Years of Solitude'.")
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
                 .fullName("Gabriel García Márquez")
                 .nationality("Colombian")
                 .birthdate(LocalDate.of(1927, 3, 6))
                 .bio("Colombian novelist, short-story writer, and journalist, known for 'One Hundred Years of Solitude'.")
                 .imageId(101L)  // Reference to an image stored elsewhere
                 .build();
     }

    public static AuthorInformation authorInformation() {
        return AuthorInformation.builder()
                .id(1L)
                .fullName("Gabriel García Márquez")
                .nationality("Colombian")
                .birthdate(LocalDate.of(1927, 3, 6))
                .bio("Colombian novelist, short-story writer, and journalist, known for 'One Hundred Years of Solitude'.")
                .image("https://example.com/images/gabo.jpg") // URL or base64 string
                .build();
     }

     public static  AuthorsSearchCriteria authorsSearchCriteria(){
         return AuthorsSearchCriteria.builder()
                 .search("Gabriel García Márquez")
                 .page(1)
                 .size(10)
                 .build();
     }

     public static ImageUpload imageUpload(){
         return ImageUpload.builder()
                 .nameFile("profile.jpg")
                 .base64("base64EncodedStringHere")
                 .idImage(1L)
                 .id(1L)
                 .build();
     }
}
