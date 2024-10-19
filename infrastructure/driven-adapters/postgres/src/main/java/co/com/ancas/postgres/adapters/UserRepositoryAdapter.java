package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.UserRepositoryport;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.UserEntity;
import co.com.ancas.postgres.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryport {

    private final UserRepository userRepository;

    @Override
    public boolean verifyExistsUserName(String userName) {
        return this.userRepository.existsByUsername(userName);
    }

    @Override
    public void save(User build) {
        this.userRepository.save(Mapper.map(build, UserEntity.class));
    }
}
