package co.com.ancas.models.enums;

public enum Messages {
    MESSAGE_GENERAL_BAD_REQUEST("Bad Request"),
    MESSAGE_GENERAL_NOT_FOUND("Not Found"),
    MESSAGE_EXCEPTION("An error occurred, please try again later or contact with support"),
    MESSAGE_USER_ERROR_USER_NOT_FOUND("User not found"),
    MESSAGE_ERROR_DNI_ALREADY_EXISTS("DNI already exists"),
    MESSAGE_ROLE_NOT_FOUND("Role %s not found"),
    MESSAGES_MEMBER_NOT_FOUND("Membership %s not found"),
    MESSAGE_ERROR_EMAIL_ALREADY_EXISTS("Email already exists"),
    MESSAGE_HEALTH_CHECK("Service is up and running");
    private final String message;

    Messages(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
