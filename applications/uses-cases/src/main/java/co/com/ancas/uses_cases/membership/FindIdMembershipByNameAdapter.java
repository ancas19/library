package co.com.ancas.uses_cases.membership;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.repositories.MembershipRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static co.com.ancas.models.enums.Messages.MESSAGES_MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Component
public class FindIdMembershipByNameAdapter implements IUseCase<String, Long> {
    private final MembershipRepositoryPort membershipRepositoryPort;
    @Override
    public Long execute(String membershipName) {
        Long idEncontrado=membershipRepositoryPort.findIdMembershipByName(membershipName);
        if (Objects.isNull(idEncontrado)){
            throw new NotFoundException(MESSAGES_MEMBER_NOT_FOUND.getMessage().formatted(membershipName));
        }
        return idEncontrado;
    }
}
