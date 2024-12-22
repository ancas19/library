package co.com.ancas.uses_cases.auth;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.models.model.Attempt;
import co.com.ancas.models.model.AuthToken;
import co.com.ancas.models.model.AuthLogin;
import co.com.ancas.models.repositories.AttemptRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import co.com.ancas.uses_cases.jwt.JwtAdapter;
import co.com.ancas.uses_cases.people.ChangeStatusPersonAdapter;
import co.com.ancas.uses_cases.people.FindPeopleFullInformationAdapter;
import co.com.ancas.uses_cases.user.UserDetailsAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class LoginAdapter implements IUseCase<AuthLogin, AuthToken> {
    private final UserDetailsAdapter userDetailsAdapter;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAdapter jwtAdapter;
    private final AttemptRepositoryPort attemptRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final ChangeStatusPersonAdapter changeStatusPersonAdapter;
    private final FindPeopleFullInformationAdapter findPeopleFullInformationAdapter;

    @Override
    public AuthToken execute(AuthLogin authLogin) throws MessagingException, IOException {
        User user = (User) userDetailsAdapter.loadUserByUsername(authLogin.getUsername());
        Long personId = userRepositoryPort.findPersonIdByUsername(authLogin.getUsername());
        if (!verifyPassword(authLogin.getPassword(), user.getPassword())) {
            verifyAttempts(authLogin.getUsername(), personId);
            throw new UnauthorizedException(Messages.MESSAGE_LOGIN_FAILED.getMessage());
        }
        String token = jwtAdapter.generateToken(user);
        jwtAdapter.saveToken(token, token);
        return AuthToken.builder()
                .token(token)
                .message(Messages.MESSAGE_LOGIN_SUCCESS.getMessage())
                .peopleFullInfomration(findPeopleFullInformationAdapter.execute(personId))
                .build();
    }
    private boolean verifyPassword(String enteredPassword, String storedPassword) {
        return passwordEncoder.matches(enteredPassword, storedPassword);
    }

    public void verifyAttempts(String username, Long personId) throws MessagingException, IOException {
        Attempt attempt = attemptRepositoryPort.find(username);
        if (Objects.isNull(attempt)) {
            saveNewAttempt(username);
            return;
        }
        incrementAttemptQuantity(attempt);
        if (attempt.getQuantity() >= 3) {
            changeStatusPersonAdapter.execute(personId);
            attemptRepositoryPort.delete(username);
        }
    }
    private void saveNewAttempt(String username) {
        Attempt attempt = Attempt.builder()
                .username(username)
                .quantity(1)
                .build();
        attemptRepositoryPort.save(attempt);
    }
    private void incrementAttemptQuantity(Attempt attempt) {
        attempt.setQuantity(attempt.getQuantity() + 1);
        attemptRepositoryPort.save(attempt);
    }
}
