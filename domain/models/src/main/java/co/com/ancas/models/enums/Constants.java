package co.com.ancas.models.enums;

public enum Constants {
    NORMAL("NORMAL"),
    USER("USER"),
    EMPLOYEE("EMPLOYEE"),
    CHARACTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$&*"),;

    private final String constant;

    Constants(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }
}
