package co.com.ancas.models.repositories;


public interface MembershipRepositoryPort {
    Long findIdMembershipByName(String membershipName);
}
