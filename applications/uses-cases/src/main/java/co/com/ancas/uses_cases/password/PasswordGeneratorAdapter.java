package co.com.ancas.uses_cases.password;

import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SecureRandom;

@Component
public class PasswordGeneratorAdapter implements IUseCase<Integer,String> {
    private  final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private  final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private  final String DIGITS = "0123456789";
    private  final String SPECIAL_CHARACTERS = "#?!@$%^&*-";
    private  final String ALL_CHARACTERS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
    private SecureRandom random = new SecureRandom();
    @Override
    public String execute(Integer integer) throws MessagingException, IOException {
        StringBuilder password = new StringBuilder();
        password.append(getRandomCharacter(UPPERCASE));
        password.append(getRandomCharacter(LOWERCASE));
        password.append(getRandomCharacter(DIGITS));
        password.append(getRandomCharacter(SPECIAL_CHARACTERS));
        for (int i = 4; i < integer; i++) {
            password.append(getRandomCharacter(ALL_CHARACTERS));
        }
        return shuffleString(password.toString());
    }
    private char getRandomCharacter(String characters) {
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }

    private  String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }
}
