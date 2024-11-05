package co.com.ancas.models.repositories;

import co.com.ancas.models.model.User;

import java.util.Optional;

public interface UserRepositoryPort {

    boolean verifyExistsUserName(String userName);
    void save(User build);
    Optional<User> findUserByUsername(String username);
}
