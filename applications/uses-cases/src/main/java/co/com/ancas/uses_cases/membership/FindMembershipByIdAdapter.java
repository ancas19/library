package co.com.ancas.uses_cases.membership;

import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.Membership;
import co.com.ancas.models.repositories.MembershipRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindMembershipByIdAdapter implements IUseCase<Long, Membership> {
    private final MembershipRepositoryPort membershipRepositoryPort;

    @Override
    public Membership execute(Long id) {
        Optional<Membership> membershipFound = membershipRepositoryPort.findMembershipById(id);
        if (membershipFound.isEmpty()) {
            log.error("Membership not found: {}", id);
            throw new NotFoundException(Messages.MESSAGE_MEMBERSHIP_NOT_FOUND.getMessage().formatted(id));
        }
        return membershipFound.get();
    }
}
