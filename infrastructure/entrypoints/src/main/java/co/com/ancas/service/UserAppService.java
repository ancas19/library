package co.com.ancas.service;

import co.com.ancas.models.model.UpdatePassword;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.ChangePasswordRequest;
import co.com.ancas.uses_cases.user.UpdatePasswordAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserAppService {
    private final UpdatePasswordAdapter updatePasswordAdapter;

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void updatePassword(ChangePasswordRequest request) throws MessagingException, IOException {
        updatePasswordAdapter.execute(Mapper.map(request, UpdatePassword.class));
    }
}
