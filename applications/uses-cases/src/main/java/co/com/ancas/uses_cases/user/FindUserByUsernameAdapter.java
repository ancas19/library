package co.com.ancas.uses_cases.user;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Component
@Slf4j
public class FindUserByUsernameAdapter implements IUseCase<String, User> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User execute(String s) throws MessagingException, IOException {
        Optional<User> userFound=userRepositoryPort.findUserByUsername(s);
        if (userFound.isEmpty()){
            log.error("User not found: {}", s);
            throw new NotFoundException(Messages.MESSAGE_USER_NOT_FOUND.getMessage());
        }
        return userFound.get();
    }
}
