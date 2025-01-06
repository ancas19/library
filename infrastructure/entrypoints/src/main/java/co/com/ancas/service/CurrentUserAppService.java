package co.com.ancas.service;


import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.ForbiddenException;
import co.com.ancas.models.model.CurrentUserInformation;
import co.com.ancas.uses_cases.user.CurrentUserAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CurrentUserAppService {
    private final CurrentUserAdapter currentUserAdapter;


    public void  verifyCurrentUserDni(String dni){
        CurrentUserInformation currentUserInformationFound=currentUserAdapter.execute();
        if(currentUserInformationFound.getRole().equalsIgnoreCase(Constants.USER.getConstant()) && !currentUserInformationFound.getDni().equalsIgnoreCase(dni)){
            throw new ForbiddenException(Messages.MESSAGE_GENERAL_FORBIDDEN.getMessage());
        }
    }

    public void verifyCurrentUsername(String username){
        CurrentUserInformation currentUserInformationFound=currentUserAdapter.execute();
        if(currentUserInformationFound.getRole().equalsIgnoreCase(Constants.USER.getConstant()) && !currentUserInformationFound.getUsername().equalsIgnoreCase(username)){
            throw new ForbiddenException(Messages.MESSAGE_GENERAL_FORBIDDEN.getMessage());
        }
    }



    public void verifyCurrentUserPersonId(Long personId){
        CurrentUserInformation currentUserInformationFound=currentUserAdapter.execute();
        if(currentUserInformationFound.getRole().equalsIgnoreCase(Constants.USER.getConstant()) && !currentUserInformationFound.getPersonId().equals(personId)){
            throw new ForbiddenException(Messages.MESSAGE_GENERAL_FORBIDDEN.getMessage());
        }
    }
}
