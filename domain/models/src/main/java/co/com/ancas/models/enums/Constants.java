package co.com.ancas.models.enums;

public enum Constants {
    NORMAL("NORMAL"),
    ACTIVE("ACTIVE"),
    USER("USER"),
    EMPLOYEE("EMPLOYEE"),
    PREMIUM("PREMIUM"),
    USER_AND_PASSWORD("USER_AND_PASSWORD"),
    SUBJECT_USER_AND_PASSWORD("User and password"),
    CODE_UNBLOCK_PERSON("Code to unblock person"),
    SUBJECT_FILES_PROCESSED("File with authors processed"),
    SUBJECT_ERROR_FILE("Error processing file with authors"),
    SUBJECT_FILES_PROCESSED_BOOKS("File with books processed"),
    SUBJECT_ERROR_FILE_BOOKS("Error processing file with books"),
    ATTEMPT("ATTEMPT_%s"),
    TOKEN("TOKEN_%s"),
    CODE("CODE_%s"),
    INACTIVE("INACTIVE"),
    ROLE("ROLE_%s"),
    UNBLOCK_USER("UNBLOCK_USER"),
    YES("YES"),
    NO("NO"),
    BOOK_LOAN("BOOK_LOAN"),
    REPLACE_LOANS("<!-- BookListPlaceholder -->"),
    BOOKS_LOAN("Books loaned"),
    DISCOUNT("DISCOUNT"),
    CLOSE_TD("</td>"),
    OPEN_TD("<td style='border: 1px solid #ddd; padding: 8px;'>"),
    OPEN_TR("<tr style='border: 1px solid #ddd;'>"),
    CLOSE_TR("</tr>"),
    ERROR_FILE("ERROR_FILE"),
    FILE_CORRECT("FILE_CORRECT"),
    MEMBERSHIP("MEMBERSHIP"),
    SUBJECT_MEMBERSHIP("Membership"),
    AUTHORIZATION("Authorization"),
    INCREASE("INCREASE"),;

    private final String constant;

    Constants(String constant) {
        this.constant = constant;
    }

    public String getConstant() {
        return constant;
    }
}
