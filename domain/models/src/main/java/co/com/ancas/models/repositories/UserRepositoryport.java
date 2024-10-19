package co.com.ancas.models.repositories;

import co.com.ancas.models.model.User;

public interface UserRepositoryport {

    boolean verifyExistsUserName(String userName);

    void save(User build);
}
