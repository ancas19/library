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
    MESSAGE_BOOK_NOT_FOUND("Books not found"),
    MESSAGE_ERROR_PERSON_NOT_FOUND("No exist person with id %s"),
    MESSAGE_ERROR_LOAN_LIMIT("The user has reached the limit of active loans"),
    MESSAGE_ERROR_BOOK_NOT_FOUND_BY_ISBN("No exist book with ISBN %s"),
    MESSAGE_ERROR_BOOK_NOT_AVAILABLE("The book(%s) is not available"),
    MESSAGE_ERROR_AVAILABLE_COPIES("Error, calculated available copies"),
    MESSAGE_ERROR_LOAN_NOT_FOUND("No loans found"),
    MESSAGE_ERROR_DATE_START_AFTER_FINISH("The start date must be less than the end date"),
    MESSAGE_BOOK_ALREADY_LOANED("The book(%s) is already loaned"),
    MESSAGE_ERROR_LOANS_FOUND("Error, loans not found"),
    MESSAGE_LOAN_RETURN_MULTIPLE_USERS("Error, trying to return loans from different users"),
    MESSAGE_ERROR_LOAN_NOT_RETURNED("Error, the user has loans without paying"),
    MESSAGE_ERROR_EXPIRED_LOANS("Error, the user has expired loans"),
    MESSAGE_ERROR_LOAN_ALREADY_RETURNED("Error, the loan has already been returned"),
    MESSAGE_ERROR_TYPE_SEARCH_NOT_FOUND("Error, type search not found"),
    MESSAGE_ERROR_RECOVERY_PASSWORD("Error, trying to recover password"),
    MESSAGE_ERROR_PASSWORDS_NOT_MATCH("Error, trying to recover password"),
    MESSAGE_ERROR_FINE_GREATER_THAN_REAL("Fee sent is greater(%s) than the real fee(%s)"),
    MESSAGE_ERROR_DOWNLOAD_IMAGE("Error downloading image"),
    MESSAGE_ERROR_AUTHOR_DATA_INVALID("Data hasn't correct lenght"),
    MESSAGE_ERROR_FILE_PROCESSING("Error processing file"),

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
    MESSAGE_BOOK_CREATED("Book created successfully"),
    MESSAGE_BOOK_FOUND("Books found successfully"),
    MESSAGE_BOOK_UPDATED("Book updated successfully"),
    MESSAGE_BOOK_IMAGE_UPDATED("Book image updated successfully"),
    MESSAGE_CHANGE_BOOK_STATUS("Book status changed successfully"),
    MESSAGE_LOAN_CREATED("Loan created successfully"),
    MESSAGE_LOANS_FOUND("Loans found successfully"),
    MESSAGE_VALUE_TO_PAY_CALCULATED("Value to pay calculated successfully"),
    MESSAGE_LOAN_RETURNED("Loan returned successfully"),
    MESSAGE_PASSWORD_CHANGED("Password changed successfully"),
    MESSAGE_AUTHOR_FILES_UPLOADED("Authors uploaded by file successfully"),
    MESSAGE_BOOK_FILES_UPLOADED("Books uploaded by file successfully"),;
    private final String message;

    Messages(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
