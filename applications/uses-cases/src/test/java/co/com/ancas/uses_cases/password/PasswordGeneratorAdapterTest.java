package co.com.ancas.uses_cases.password;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PasswordGeneratorAdapterTest {

    @InjectMocks
    private PasswordGeneratorAdapter passwordGeneratorAdapter;

    @Test
    void generatePassword() throws MessagingException, IOException {
        //Act
        String password = passwordGeneratorAdapter.execute(20);
        //Assert
        assertNotNull(password);
    }
}