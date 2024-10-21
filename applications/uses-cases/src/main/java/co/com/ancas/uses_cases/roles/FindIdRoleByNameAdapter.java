package co.com.ancas.uses_cases.roles;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.repositories.MembershipRepositoryPort;
import co.com.ancas.models.repositories.RolesRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static co.com.ancas.models.enums.Messages.MESSAGES_MEMBER_NOT_FOUND;
import static co.com.ancas.models.enums.Messages.MESSAGE_ROLE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class FindIdRoleByNameAdapter implements IUseCase<String, Long> {
    private final RolesRepositoryPort rolesRepositoryPort;
    @Override
    public Long execute(String roleName) {
        Long idEncontrado=rolesRepositoryPort.findIdRoleByName(roleName);
        if(Objects.isNull(idEncontrado)){
            throw new NotFoundException(MESSAGE_ROLE_NOT_FOUND.getMessage().formatted(roleName));
        }
        return idEncontrado;
    }
}
