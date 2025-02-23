package co.com.ancas.service;

import co.com.ancas.models.model.*;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.request.*;
import co.com.ancas.response.PaginationResponse;
import co.com.ancas.response.PeopleFullInfomrationResponse;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.uses_cases.people.*;
import co.com.ancas.uses_cases.user.RecoveryPasswordAdapter;
import co.com.ancas.utils.Pagination;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PeopleAppService {
    private final CreatePersonAdapter createPersonAdapter;
    private final FindPeopleByCriteriaAdapter findPeopleByCriteriaAdapter;
    private final FindPeopleFullInformationAdapter findPeopleByIdAdapter;
    private final UploadProfileImageAdapter uploadProfileImageAdapter;
    private final UpdatePersonAdapter updatePersonAdapter;
    private final ChangeStatusPersonAdapter changeStatusPersonAdapter;
    private final SendCodeToUnblockPersonAdapter sendCodeToUnblockPersonAdapter;
    private final UnblockPeopleAdapter unblockPeopleAdapter;
    private final CurrentUserAppService currentUserAppService;
    private final RecoveryPasswordAdapter recoveryPasswordAdapter;

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public PeopleResponse createPeople(PeopleRequest request) throws MessagingException, IOException {
        return Mapper.map(createPersonAdapter.execute(Mapper.map(request, PeopleCreation.class)), PeopleResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class ,readOnly = true)
    public PaginationResponse<PeopleResponse> findAllByCriteria(PeopleSearchCriteriaRequest request, Integer page,Integer size) throws MessagingException {
       PeopleSearchCriteria peopleSearchCriteria = Mapper.map(request, PeopleSearchCriteria.class);
       peopleSearchCriteria.setPage(page);
       peopleSearchCriteria.setSize(size);
       return Pagination.getPaginationResponse(findPeopleByCriteriaAdapter.execute(peopleSearchCriteria), PeopleResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class ,readOnly = true)
    public PeopleFullInfomrationResponse findById(Long id) throws MessagingException {
        currentUserAppService.verifyCurrentUserPersonIdAndRole(id);
        return Mapper.map(findPeopleByIdAdapter.execute(id), PeopleFullInfomrationResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void uploadProfileImage(ImageUploadRequest imageUpload) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentPersonId(imageUpload.getId());
        this.uploadProfileImageAdapter.execute(Mapper.map(imageUpload, ImageUpload.class));
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public PeopleResponse updatePeople(PeopleInformationRequest request) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserPersonIdAndRole(request.getId());
        return Mapper.map(updatePersonAdapter.execute(Mapper.map(request, People.class)), PeopleResponse.class);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void blockPeople(Long id) throws MessagingException, IOException {
        currentUserAppService.verifyCurrentUserPersonIdAndRole(id);
        changeStatusPersonAdapter.execute(id);
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void sendCodeToUnblockPeople(PersonAccessRequest personAccessRequest) throws MessagingException, IOException {
        sendCodeToUnblockPersonAdapter.execute(Mapper.map(personAccessRequest, PersonAccess.class));
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void unblockPeople(PersonCodeRequest personCodeRequest) throws MessagingException, IOException {
        this.unblockPeopleAdapter.execute(Mapper.map(personCodeRequest, PersonCode.class));
    }

    @Transactional(value = "libraryTransactionManager",rollbackFor = Exception.class)
    public void recoveryPassword(PasswordRecoveryRequest passwordRecoveryRequest) throws MessagingException, IOException {
        this.recoveryPasswordAdapter.execute(Mapper.map(passwordRecoveryRequest, PasswordRecovery.class));
    }
}
