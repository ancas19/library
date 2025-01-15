package co.com.ancas.models.repositories;

import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserInformation;
import co.com.ancas.models.model.UserMembershipInfo;

import java.util.Optional;

public interface UserRepositoryPort {

    boolean verifyExistsUserName(String userName);
    void save(User build);
    Optional<User> findUserByUsername(String username);
    Optional<UserInformation> findUserByPersonId(Long idPersona);
    String findRoleByUserId(Long id);
    Long findPersonIdByUsername(String username);
    Optional<UserMembershipInfo> findUserAndMembershipInfo(String s);
    CurrentUserInformation findCurrentUserInformation(String username);
    Optional<UserMembershipInfo> findUserAndMembershipInfoByUserId(Long s);
    Optional<User> findUserByEmail(String email);
}
