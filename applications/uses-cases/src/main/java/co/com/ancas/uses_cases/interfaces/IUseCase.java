package co.com.ancas.uses_cases.interfaces;

import jakarta.mail.MessagingException;

import java.io.IOException;

@FunctionalInterface
public interface IUseCase<Input, Output> {

    Output execute(Input input) throws MessagingException, IOException;
}
