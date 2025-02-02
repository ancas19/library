package co.com.ancas.uses_cases.user;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.UserMembershipInfo;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class FindUserAndMembershipInfoByUserIdAdapter implements IUseCase<Long, UserMembershipInfo> {
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserMembershipInfo execute(Long s) throws MessagingException, IOException {
        Optional<UserMembershipInfo> userMembershipInfoFound = userRepositoryPort.findUserAndMembershipInfoByUserId(s);
        if (userMembershipInfoFound.isEmpty()){
            throw new NotFoundException(Messages.MESSAGE_ERROR_PERSON_NOT_FOUND.getMessage().formatted(s));
        }
        return userMembershipInfoFound.get();
    }
}
