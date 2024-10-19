package co.com.ancas.postgres.adapters;

import co.com.ancas.models.repositories.RolesRepositoryPort;
import co.com.ancas.postgres.repositories.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesRepositoryAdapter implements RolesRepositoryPort {
    private final RolesRepository rolesRepository;
    @Override
    public Long findIdRoleByName(String roleName) {
        return this.rolesRepository.findIdRoleByName(roleName);
    }
}
