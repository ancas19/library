package co.com.ancas.uses_cases.util;


import java.util.Random;

public class RandomCode {

    private static final Random random = new Random(); // Make Random instance static

    public static String generateRandomCode() {
        return String.valueOf(random.nextInt(900000) + 100000);
    }
}
