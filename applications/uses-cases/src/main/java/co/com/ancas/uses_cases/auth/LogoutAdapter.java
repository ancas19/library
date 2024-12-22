package co.com.ancas.uses_cases.user;

import co.com.ancas.models.repositories.JwtRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import co.com.ancas.uses_cases.jwt.JwtAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LogoutAdapter implements IUseCaseVoid<String> {
    private final JwtAdapter jwtAdapter;

    @Override
    public void execute(String token) {
        jwtAdapter.deleteToken(token);
    }
}
