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

    //Success messages
    MESSAGE_PEOPLE_FULL_INFOMRATION("Person found successfully"),
    MESSAGE_HEALTH_CHECK("Service is up and running"),
    MESSAGE_PEOPLE_CREATED("Person created successfully"),
    MESSAGE_PEOPLE_FOUND("People found successfully"),
    MESSAGE_IMAGE_UPLOAD("Image uploaded successfully"),
    MESSAGE_PEOPLE_UPDATED("Person updated successfully"),
    MESSAGE_PEOPLE_BLOCKED("Person blocked successfully"),
    MESSAGE_PASSWORD_UPDATED("Password updated successfully"),;
    private final String message;

    Messages(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
