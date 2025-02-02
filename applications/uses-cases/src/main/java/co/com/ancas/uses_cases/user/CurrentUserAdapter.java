package co.com.ancas.uses_cases.user;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.UnauthorizedException;
import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.model.TokenInformation;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseResult;
import co.com.ancas.uses_cases.jwt.JwtAdapter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrentUserAdapter implements IUseCaseResult<CurrentUserInformation> {
    private final JwtAdapter jwtAdapter;
    private final HttpServletRequest request;
    @Override
    public CurrentUserInformation execute() {
        String authorizationHeader = request.getHeader(Constants.AUTHORIZATION.getConstant()).substring(7);
        TokenInformation tokenInformation = jwtAdapter.getTokenInformation(authorizationHeader);
        if (Objects.isNull(tokenInformation)) {
            log.error("Token information is null");
            throw new UnauthorizedException(Messages.MESSAGE_GENERAL_UNAUTHORIZED.getMessage());
        }
        return CurrentUserInformation.builder()
                .username(tokenInformation.getUsername())
                .userId(tokenInformation.getIdUser())
                .personId(tokenInformation.getIdPersona())
                .dni(tokenInformation.getDni())
                .role(tokenInformation.getRole())
                .build();
    }
}
