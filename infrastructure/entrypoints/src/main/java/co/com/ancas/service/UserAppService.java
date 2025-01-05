package co.com.ancas.service;

import co.com.ancas.models.model.UpdatePassword;
import co.com.ancas.models.model.UserInformation;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.ChangePasswordRequest;
import co.com.ancas.response.UserInformationResponse;
import co.com.ancas.uses_cases.user.FindUserByPersonIdAdapter;
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
    private final FindUserByPersonIdAdapter findUserByPersonIdAdapter;
    private final CurrentUserAppService currentUserAppService;

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void updatePassword(ChangePasswordRequest request) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUsername(request.getUsername());
        updatePasswordAdapter.execute(Mapper.map(request, UpdatePassword.class));
    }

    @Transactional(value = "libraryTransactionManager",readOnly = true,rollbackFor = Exception.class)
    public UserInformationResponse findUserByPersonid(Long id) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserPersonId(id);
        return Mapper.map(this.findUserByPersonIdAdapter.execute(id),UserInformationResponse.class);
    }
}
