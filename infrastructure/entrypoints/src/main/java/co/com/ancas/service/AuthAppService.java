package co.com.ancas.service;

import co.com.ancas.models.model.AuthLogin;
import co.com.ancas.models.model.AuthToken;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.AuthLoginRequest;
import co.com.ancas.response.AuthTokenResponse;
import co.com.ancas.uses_cases.user.LoginAdapter;
import co.com.ancas.uses_cases.user.LogoutAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthAppService {
    private final LoginAdapter loginAdapter;
    private final LogoutAdapter logoutAdapter;
    public AuthTokenResponse login(AuthLoginRequest request) throws MessagingException, IOException {
        return Mapper.map(loginAdapter.execute(Mapper.map(request, AuthLogin.class)), AuthTokenResponse.class);
    }
    public void logout(String token) {
        logoutAdapter.execute(token);
    }
}
