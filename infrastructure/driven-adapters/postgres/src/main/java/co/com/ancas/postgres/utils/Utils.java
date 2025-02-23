package co.com.ancas.postgres.utils;

public class Utils {
    private Utils() {}
    public static String forrmatStringSearch(String value) {
        return "%%%s%%".formatted(value);
    }
}
