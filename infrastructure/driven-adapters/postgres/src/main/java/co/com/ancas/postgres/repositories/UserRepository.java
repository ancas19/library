package co.com.ancas.postgres.repositories;

import co.com.ancas.models.utils.Mapper;
import co.com.ancas.postgres.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByUsername(String userName);
    Optional<UserEntity> findUserByUsername(String username);
}
