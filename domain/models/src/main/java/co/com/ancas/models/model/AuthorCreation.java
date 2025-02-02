package co.com.ancas.models.model;

import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.utils.Constants;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthorCreation {
    private Long id;
    private String fullName;
    private String nationality;
    private LocalDate birthdate;
    private String bio;
    private String nameFile;
    private String base64;


    public void validate() {
        if (Objects.isNull(fullName) || fullName.isEmpty()) {
            throw new BadRequestException(Constants.NAME_REQUIRED);
        }
        if (!fullName.matches(Constants.LETTERS)) {
            throw new BadRequestException(Constants.NAME_INVALID);
        }

        if (Objects.isNull(nationality) || nationality.isEmpty()) {
            throw new BadRequestException(Constants.NATIONALITY_REQUIRED);
        }
        if (!nationality.matches("^[A-Z ]+$")) {
            throw new BadRequestException(Constants.NATIONALITY_INVALID);
        }

        if (Objects.isNull(birthdate)) {
            throw new BadRequestException(Constants.BIRTHDATE_REQUIRED);
        }
        if (birthdate.isAfter(LocalDate.now())) {
            throw new BadRequestException(Constants.BIRTHDATE_INVALID);
        }

        if (Objects.isNull(bio) || bio.isEmpty()) {
            throw new BadRequestException(Constants.BIO_REQUIRED);
        }
        if (!bio.matches("^[a-zA-ZÁÉÍÓÚÑáéíóúñ0-9., @()_-]+$")) {
            throw new BadRequestException(Constants.BIO_INVALID);
        }

        if (Objects.isNull(nameFile) || nameFile.isEmpty()) {
            throw new BadRequestException(Constants.NAME_FILE_REQUIRED);
        }
        if (Objects.isNull(base64) || base64.isEmpty()) {
            throw new BadRequestException(Constants.BASE64_REQUIRED);
        }
    }

    public static AuthorCreation authroFromFile(String fullName, String nationality, LocalDate birthdate, String bio, String nameFile, String base64){
        AuthorCreation author= AuthorCreation.builder()
                .fullName(fullName)
                .nationality(nationality)
                .birthdate(birthdate)
                .bio(bio)
                .nameFile(nameFile)
                .base64(base64)
                .build();
        author.validate();
        return author;
    }

}
