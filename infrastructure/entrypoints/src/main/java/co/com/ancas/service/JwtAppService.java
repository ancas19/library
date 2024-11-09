package co.com.ancas.service;

import co.com.ancas.uses_cases.jwt.JwtAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAppService {
    private final JwtAdapter jwtAdapter;

    public String extractUsername(String token) {
        return jwtAdapter.extractUsername(token);
    }
}
