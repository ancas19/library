package co.com.ancas.uses_cases.user;

import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class UserDetailsAdapter implements UserDetailsService {
    private final UserRepositoryPort userRepositoryPort;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFound=userRepositoryPort.findUserByUsername(username);
        if(userFound.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(userFound.get().getUsername(), userFound.get().getPassword(), getAuthority(userFound.get()));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        String role=userRepositoryPort.findRoleByUserId(user.getId());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }
}
