package co.com.ancas.models.repositories;

import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserInformation;

import java.util.Optional;

public interface UserRepositoryPort {

    boolean verifyExistsUserName(String userName);
    void save(User build);
    Optional<User> findUserByUsername(String username);
    Optional<UserInformation> findUserByPersonId(Long idPersona);
    String findRoleByUserId(Long id);
    Long findPersonIdByUsername(String username);
}
