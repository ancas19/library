package co.com.ancas.uses_cases.user;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.models.model.Code;
import co.com.ancas.models.model.PasswordRecovery;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.CodeRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RecoveryPasswordAdapter implements IUseCaseVoid<PasswordRecovery> {
    private final UserRepositoryPort userRepositoryPort;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CodeRepositoryPort codeRepositoryPort;
    @Override
    public void execute(PasswordRecovery passwordRecovery) throws MessagingException, IOException {
        Optional<User> userFound=this.userRepositoryPort.findUserByEmail(passwordRecovery.getEmail());
        if (userFound.isEmpty()){
            log.error("User not found with email {}", passwordRecovery.getEmail());
            throw new UnauthorizedException(Messages.MESSAGE_ERROR_RECOVERY_PASSWORD.getMessage());
        }
        if(!passwordRecovery.getPassword().equals(passwordRecovery.getPasswordRepeat())){
            log.error("Passwords do not match");
            throw new UnauthorizedException(Messages.MESSAGE_ERROR_PASSWORDS_NOT_MATCH.getMessage());
        }
        Code codeFound=codeRepositoryPort.find(passwordRecovery.getEmail());
        if(Objects.isNull(codeFound) || !codeFound.getCode().equals(passwordRecovery.getCode())){
            log.error("Code not found or does not match");
            throw new UnauthorizedException(Messages.MESSAGE_ERROR_RECOVERY_PASSWORD.getMessage());
        }
        userFound.get().setPassword(passwordEncoder.encode(passwordRecovery.getPassword()));
        this.userRepositoryPort.save(userFound.get());
        codeRepositoryPort.delete(codeFound.getEmail());
    }
}
