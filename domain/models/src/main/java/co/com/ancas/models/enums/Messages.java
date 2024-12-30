package co.com.ancas.models.enums;

public enum Messages {
    //Error messages
    MESSAGE_GENERAL_BAD_REQUEST("Bad Request, error in the request"),
    MESSAGE_GENERAL_NOT_FOUND("Not Found, resource not found"),
    MESSAGE_EXCEPTION("An error occurred, please try again later or contact with support"),
    MESSAGE_ERROR_DNI_ALREADY_EXISTS("DNI already exists"),
    MESSAGE_ROLE_NOT_FOUND("Role %s not found"),
    MESSAGES_MEMBER_NOT_FOUND("Membership %s not found"),
    MESSAGE_ERROR_EMAIL_ALREADY_EXISTS("Email already exists"),
    MESSAGE_PEOPLE_NOT_FOUND("No people found matching the specified criteria."),
    MESSAGE_ERROR_DATA_INCORRECT("Data sent is incorrect"),
    MESSAGES_EMAIL_TEMPLATE_NOT_FOUND("Error sending email, please try again later or contact with support"),
    MESSAGE_USER_NOT_FOUND("User not exist"),
    MESSAGE_ERROR_PASSWORDS_DO_NOT_MATCH("Passwords do not match"),
    MESSAGE_GENERAL_FORBIDDEN("Forbidden, you don't have permission to access this resource"),
    MESSAGE_GENERAL_UNAUTHORIZED("Unauthorized"),
    MESSAGE_USER_BLOCKED("User is blocked, please unblocked first to login"),
    MESSAGES_EMAIL_NOT_FOUND("The email address you entered is not registered. Please double-check or sign up for a new account."),
    MESSAGE_LOGIN_FAILED("Login failed, please check your credentials"),
    MESSAGE_AUTHOR_NOT_FOUND("Authors not found"),
    MESSAGE_AUTHOR_NOT_FOUND_BY_ID("Author not found"),
    MESSAGE_ERROR_AUTHOR_NAME_ALREADY_EXISTS("Author name already exists"),
    MESSAGE_ERROR_AUTHOR_NOT_FOUND_BY_NAME("No exist author with name %s"),
    MESSAGE_ERROR_ISBN_ALREADY_EXISTS("Already exists a book with this ISBN %s"),
    MESSAGE_ERROR_TITLE_ALREADY_EXISTS("Already exists a book with this title %s"),
    MESSAGE_GENRES_NOT_FOUND("Genres not found"),
    MESSAGE_ERROR_GENRE_NOT_FOUND_BY_VALUE("Genre not found by value %s"),



    //Success messages
    MESSAGE_PEOPLE_FULL_INFOMRATION("Person found successfully"),
    MESSAGE_USER_INFOMRATION("User found successfully"),
    MESSAGE_HEALTH_CHECK("Service is up and running"),
    MESSAGE_PEOPLE_CREATED("Person created successfully"),
    MESSAGE_PEOPLE_FOUND("People found successfully"),
    MESSAGE_IMAGE_UPLOAD("Image uploaded successfully"),
    MESSAGE_PEOPLE_UPDATED("Person updated successfully"),
    MESSAGE_PEOPLE_BLOCKED("Person blocked successfully"),
    MESSAGE_PEOPLE_UNBLOCKED("Person unblocked successfully"),
    MESSAGE_SEND_CODE("Please check your email for further instructions."),
    MESSAGE_PASSWORD_UPDATED("Password updated successfully"),
    MESSAGES_CODE_NOT_FOUND("Error, code is incorrect,please try again"),
    MESSAGE_LOGIN_SUCCESS("Login success"),
    MESSAGE_LOGOUT_SUCCESSFUL("Logout successful"),
    MESSAGE_AUTHOR_CREATED("Author created successfully"),
    MESSAGE_AUTHOR_FOUND("Authors found successfully"),
    MESSAGE_AUTHOR_IMAGE_UPDATED("Author image updated successfully"),
    MESSAGE_AUTHOR_UPDATED("Author updated successfully"),
    MESSAGE_GENRES_FOUND("Genres found"),
    MESSAGE_BOOK_CREATED("Book created successfully"),;
    private final String message;

    Messages(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
