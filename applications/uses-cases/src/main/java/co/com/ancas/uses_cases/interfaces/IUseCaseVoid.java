package co.com.ancas.uses_cases.interfaces;

import jakarta.mail.MessagingException;

@FunctionalInterface
public interface IUseCaseVoid<INPUT> {
    void execute(INPUT input) throws MessagingException;
}
