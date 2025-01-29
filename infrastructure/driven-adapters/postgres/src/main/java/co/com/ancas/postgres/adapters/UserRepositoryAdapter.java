package co.com.ancas.postgres.adapters;

import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserInformation;
import co.com.ancas.models.model.UserMembershipInfo;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.UserEntity;
import co.com.ancas.postgres.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    @Override
    public boolean verifyExistsUserName(String userName) {
        return this.userRepository.existsByUsername(userName);
    }

    @Override
    public void save(User build) {
        this.userRepository.save(Mapper.map(build, UserEntity.class));
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.userRepository
                .findUserByUsername(username)
                .map(user->Mapper.map(user,User.class));
    }

    @Override
    public Optional<UserInformation> findUserByPersonId(Long idPersona) {
        return this.userRepository.findUserByPersonId(idPersona);
    }

    @Override
    public String findRoleByUserId(Long id) {
        return this.userRepository.findRoleByUserId(id);
    }

    @Override
    public Long findPersonIdByUsername(String username) {
        return this.userRepository.findPersonIdByUsername(username);
    }

    @Override
    public Optional<UserMembershipInfo> findUserAndMembershipInfo(String s) {
        return this.userRepository.findUserAndMembershipInfo(s);
    }

    @Override
    public CurrentUserInformation findCurrentUserInformation(String username) {
        return this.userRepository.findCurrentUserInformation(username);
    }

    @Override
    public Optional<UserMembershipInfo> findUserAndMembershipInfoByUserId(Long s) {
        return this.userRepository.findUserAndMembershipInfoByUserId(s);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email).map(user->Mapper.map(user,User.class));
    }

    @Override
    public List<String> findEmailsAdmins() {
        return this.userRepository.findEmailsAdmins();
    }

    @Override
    public Optional<User> findUserFindDni(String s) {
        return this.userRepository.findUserFindDni(s).map(user->Mapper.map(user,User.class));
    }

    @Override
    public boolean findChangePasswordByUsername(String username) {
        return this.userRepository.findChangePasswordByUsername(username);
    }
}
