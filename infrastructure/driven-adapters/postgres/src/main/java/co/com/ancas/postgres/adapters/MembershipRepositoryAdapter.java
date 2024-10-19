package co.com.ancas.postgres.adapters;

import co.com.ancas.models.repositories.MembershipRepositoryPort;
import co.com.ancas.postgres.repositories.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MembershipRepositoryAdapter implements MembershipRepositoryPort {
    private final MembershipRepository membershipRepository;
    @Override
    public Long findIdMembershipByName(String membershipName) {
        return this.membershipRepository.findIdMembershipByName(membershipName);
    }
}
