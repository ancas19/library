package co.com.ancas.service;

import co.com.ancas.uses_cases.user.UserDetailsAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsAppService {
    private final UserDetailsAdapter userDetailsAdapter;

    public UserDetails loadUserByUsername(String username) {
        return userDetailsAdapter.loadUserByUsername(username);
    }

}
