package co.com.ancas.uses_cases.user;

import co.com.ancas.models.model.AuthToken;
import co.com.ancas.models.model.AuthLogin;
import co.com.ancas.models.model.User;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import co.com.ancas.uses_cases.jwt.JwtAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LoginAdapter implements IUseCase<AuthLogin, AuthToken> {
    private final AuthenticationManager authenticationManager;
    private final FindUserByUsernameAdapter findUserByUsernameAdapter;
    private final JwtAdapter jwtAdapter;
    @Override
    public AuthToken execute(AuthLogin authLogin) throws MessagingException, IOException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLogin.getUsername(),
                        authLogin.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtAdapter.generateToken(authentication);
        return AuthToken.builder()
                .token(token)
                .message("Login successful")
                .build();
    }
}
