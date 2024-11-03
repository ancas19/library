package co.com.ancas.models.enums;

public enum Constants {
    NORMAL("NORMAL"),
    ACTIVE("ACTIVE"),
    USER("USER"),
    EMPLOYEE("EMPLOYEE"),
    USER_AND_PASSWORD("USER_AND_PASSWORD"),
    SUBJECT_USER_AND_PASSWORD("User and password"),
    CHARACTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$&*"),;

    private final String constant;

    Constants(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }
}
