package co.com.ancas.models.utils;

public class Constants {


    private Constants() {
    }
    public static final String PHONE ="^\\+[1-9][0-9]{1,3}[0-9]{6,14}$" ;
    public static final String ISBN ="\"^(?:\\\\d{9}X|\\\\d{10}|\\\\d{13})$";
    public static final String LETTERS_AND_NUMBERS ="^[0-9A-ZÁÉÍÓÚÑ ]+$";
    public static final String LETTERS ="^[A-ZÁÉÍÓÚÑ ]+$";
    public static final String LETTERS_AND_SPECIAL_CHARACTERS ="^[a-zA-ZÁÉÍÓÚÑáéíóúñ0-9., @()_-]+$";
    public static final String USERNAME="^[A-Z0-9.]+$";
    public static final String NATIONALITY="^[A-Z ]+$";
    public static final String NAME_FILE="^[a-zA-Z0-9.-_ ]+$";
    public static final String SEARCH_PARAMETERS="^[a-zA-Z0-9 ]+$";
    public static final String PASSWORD_REGEX="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    public static final String CODE="^\\d{6}$";
    public static final String AVAILABLE= "YES|NO";
    public static final String SEARCH="^[0-9a-zA-ZáéíóúÁÉÍÓÚÑ ]*$";
    public static final String DNI="^\\d{8,15}$";
    public static final String COMMENT="^[\\p{L}0-9.,:;!?()'\" \\t\\n\\-]+$\n";
    public static final String SEARCH_BOOK ="^[0-9a-záéíóúñ ]*$" ;
    public static final String TYPE_SEARCH ="HISTORICAL|RETURNED|EXPIRED|ACTIVE" ;
    public static final String ROLE="EMPLOYEE|USER|ADMIN" ;


    public static final String SEARCH_REQUIRED ="Search is required";
    public static final String ROLE_INVALID="User type must be 'ADMIN', 'EMPLOYEE' or 'USER'";
    public static final String ID_REQUIRED = "Id is required";
    public static final String ID_INVALID = "Id must be positive";
    public static final String USERNAME_INVALID= "Username must have only letters and numbers in uppercase";
    public static final String TITLE_REQUIRED = "Title is required";
    public static final String TITLE_INVALID = "Title must have only letters, numbers, and spaces";
    public static final String ISBN_REQUIRED = "ISBN is required";
    public static final String ISBN_INVALID = "ISBN must have 10 or 13 digits";
    public static final String AUTHOR_REQUIRED = "Author name is required";
    public static final String AUTHOR_INVALID = "Author name must have only letters and spaces";
    public static final String PUBLISH_DATE_REQUIRED = "Publish date is required";
    public static final String PUBLISH_DATE_INVALID = "Publish date must be in the past";
    public static final String GENRE_REQUIRED = "Genre is required";
    public static final String GENRE_INVALID = "Genre must have only letters and spaces";
    public static final String AVAILABLE_COPIES_INVALID = "Available copies must be greater than or equal to 0";
    public static final String AVAILABLE_REQUIRED = "Available is required";
    public static final String AVAILABLE_INVALID = "Available must be 'SI' or 'NO'";
    public static final String BLURB_REQUIRED = "Blurb is required";
    public static final String BLURB_INVALID = "Blurb must have only letters, spaces, and special characters";
    public static final String NAME_FILE_REQUIRED = "Name file is required";
    public static final String NAME_FILE_INVALID = "Name file must have only letters and numbers";
    public static final String BASE64_REQUIRED = "Base64 is required";
    public static final String USERNAME_REQUIRED = "Username is required";
    public static final String PASSWORD_REQUIRED = "Password is required";
    public static final String PASSWORD_INVALID = "Password must have at least 8 characters, one uppercase letter, one lowercase letter, one number, and one special character";
    public static final String NAME_REQUIRED = "Name is required";
    public static final String LAST_NAME_REQUIRED = "Lastname is required";
    public static final String NAME_INVALID = "Name must have only letters and spaces";
    public static final String NATIONALITY_REQUIRED = "Nationality is required";
    public static final String NATIONALITY_INVALID = "Nationality must have only letters and spaces";
    public static final String BIRTHDATE_REQUIRED = "Birthdate is required";
    public static final String BIRTHDATE_INVALID = "Birthdate must be in the past";
    public static final String BIO_REQUIRED = "Bio is required";
    public static final String BIO_INVALID = "Bio must have only letters, spaces, and special characters";
    public static final String SEARCH_PARAMETERS_REQUIRED = "Search parameter is required";
    public static final String SEARCH_PARAMETERS_INVALID = "Search parameter must have only letters, numbers and spaces";
    public static final String EMAIL_REQUIRED ="Email is required";
    public static final String CODE_REQUIRED ="Code is required";
    public static final String CODE_INVALID ="Code must have 6 digits and only numbers";
    public static final String SEARCH_INVALID ="Search must have only letters, digits and spaces" ;
    public static final String CONFIRM_PASSWORD_REQUIRED = "Confirm password is required";
    public static final String DNI_INVALID ="DNI must have 8 to 15 digits";
    public static final String EXTENSION_REQUIRED = "Extension is required";
    public static final String COMMENT_INVALID = "Comment must have only letters, digits, spaces, and special characters";
    public static final String SEARCH_BOOK_INVALID ="Search book only can have letters, numbers, spaces and accents" ;
    public static final String TYPE_SEARCH_INVALID="Type search must be HISTORICAL, RETURNED, EXPIRED or ACTIVE";
    public static final String DNI_REQUIRED ="DNI is required";
    public static final String LAST_NAME_INVALID = "Last name must have only letters and spaces";
    public static final String PHONE_INVALID = "Phone must have 10 digits";
    public static final String PHONE_REQUIRED = "Phone is required";
    public static final String ROLE_REQUIRED = "Role is required";


}
