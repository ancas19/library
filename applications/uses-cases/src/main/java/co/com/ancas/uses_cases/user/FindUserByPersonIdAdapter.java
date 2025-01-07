package co.com.ancas.uses_cases.user;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserInformation;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class FindUserByPersonIdAdapter implements IUseCase<Long, UserInformation> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserInformation execute(Long idPersona) throws MessagingException, IOException {
        Optional<UserInformation> userFound=userRepositoryPort.findUserByPersonId(idPersona);
        if (userFound.isEmpty()){
            log.error("User not found: {}", idPersona);
            throw new NotFoundException(Messages.MESSAGE_USER_NOT_FOUND.getMessage());
        }
        return userFound.get();
    }
}
