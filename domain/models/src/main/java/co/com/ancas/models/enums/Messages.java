package co.com.ancas.models.enums;

public enum Messages {
    MESSAGE_GENERAL_BAD_REQUEST("Bad Request"),
    MESSAGE_GENERAL_NOT_FOUND("Not Found"),
    MESSAGE_EXCEPTION("An error occurred, please try again later or contact with support"),
    MESSAGE_USER_ERROR_USER_NOT_FOUND("User not found"),
    MESSAGE_HEALTH_CHECK("Service is up and running");
    private final String message;

    Messages(String message) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
