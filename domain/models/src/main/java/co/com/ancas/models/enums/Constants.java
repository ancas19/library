package co.com.ancas.models.enums;

public enum Constants {
    NORMAL("NORMAL"),
    ACTIVE("ACTIVE"),
    USER("USER"),
    EMPLOYEE("EMPLOYEE"),
    USER_AND_PASSWORD("USER_AND_PASSWORD"),
    SUBJECT_USER_AND_PASSWORD("User and password"),
    CODE_UNBLOCK_PERSON("Code to unblock person"),
    ATTEMPT("ATTEMPT_%s"),
    TOKEN("TOKEN_%s"),
    CODE("CODE_%s"),
    CHARACTERS("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$&*"),
    INACTIVE("INACTIVE"),
    ROLE("ROLE_%s"),
    UNBLOCK_USER("UNBLOCK_USER"),
    YES("YES"),
    NO("NO"),
    BOOK_LOAN("BOOK_LOAN"),
    REPLACE_LOANS("<!-- BookListPlaceholder -->"),
    BOOKS_LOAN("Books loaned"),;

    private final String constant;

    Constants(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }
}
