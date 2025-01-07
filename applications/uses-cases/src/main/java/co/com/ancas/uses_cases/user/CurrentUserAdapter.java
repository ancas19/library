package co.com.ancas.uses_cases.user;

import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserAdapter implements IUseCaseResult<CurrentUserInformation> {
    private final UserRepositoryPort userRepositoryPort;
    @Override
    public CurrentUserInformation execute() {
        String username= SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepositoryPort.findCurrentUserInformation(username);
    }
}
