package co.com.ancas.uses_cases.user;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.UpdatePassword;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class UpdatePasswordAdapter implements IUseCaseVoid<UpdatePassword> {

    private final UserRepositoryPort userRepositoryPort;
    private final FindUserByUsernameAdapter findUserByUsernameAdapter;
    private final BCryptPasswordEncoder passwordEncoder;

    public void execute(UpdatePassword updatePassword) throws MessagingException, IOException {
        if(!updatePassword.getPassword().equals(updatePassword.getConfirmPassword())) {
            throw new BadRequestException(Messages.MESSAGE_ERROR_PASSWORDS_DO_NOT_MATCH.getMessage());
        }
        User userFound = findUserByUsernameAdapter.execute(updatePassword.getUsername());
        userFound.setPassword(passwordEncoder.encode(updatePassword.getPassword()));
        userFound.setChangePassword(false);
        userRepositoryPort.save(userFound);
    }
}
