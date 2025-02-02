package co.com.ancas.uses_cases.interfaces;

import jakarta.mail.MessagingException;

import java.io.IOException;

@FunctionalInterface
public interface IUseCaseVoid<Input> {
    void execute(Input input) throws MessagingException, IOException;
}
