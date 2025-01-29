package co.com.ancas.models.repositories;


import co.com.ancas.models.model.Membership;

import java.util.Optional;

public interface MembershipRepositoryPort {
    Long findIdMembershipByName(String membershipName);
    Optional<Membership> findMembershipById(Long id);
}
