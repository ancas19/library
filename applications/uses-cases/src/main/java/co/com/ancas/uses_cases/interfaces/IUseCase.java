package co.com.ancas.uses_cases.interfaces;

import jakarta.mail.MessagingException;

@FunctionalInterface
public interface IUseCase<INPUT, OUTPUT> {

    OUTPUT execute(INPUT input) throws MessagingException;
}
