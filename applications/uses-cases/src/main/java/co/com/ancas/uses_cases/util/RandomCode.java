package co.com.ancas.uses_cases.util;

public class RandomCode {
    private RandomCode() {
    }
    public static String generateRandomCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }
}
