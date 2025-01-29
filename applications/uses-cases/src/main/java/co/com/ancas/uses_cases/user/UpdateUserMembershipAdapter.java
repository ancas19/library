package co.com.ancas.uses_cases.user;

import co.com.ancas.models.model.Membership;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import co.com.ancas.uses_cases.membership.FindIdMembershipByNameAdapter;
import co.com.ancas.uses_cases.membership.FindMembershipByIdAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static co.com.ancas.models.enums.Constants.EMPLOYEE;
import static co.com.ancas.models.enums.Constants.PREMIUM;


@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserMembershipAdapter implements IUseCaseVoid<String> {
    private final FindIdMembershipByNameAdapter findIdMembershipByNameAdapter;
    private final FindUserFindDniAdapter findUserFindDniAdapter;
    private final FindMembershipByIdAdapter findMembershipByIdAdapter;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void execute(String s) throws MessagingException, IOException {
        User userFoud=findUserFindDniAdapter.execute(s);
        Membership membershipUserFound= findMembershipByIdAdapter.execute(userFoud.getMembershipId());
        if(membershipUserFound.getMembershipType().equals(EMPLOYEE.getConstant()) || membershipUserFound.getMembershipType().equals(PREMIUM.getConstant())){
            log.error("The user cannot update the membership {}", membershipUserFound);
            throw new IllegalArgumentException("The user is already an employee");
        }
        Long idMembership=findIdMembershipByNameAdapter.execute(PREMIUM.getConstant());
        userFoud.setMembershipId(idMembership);
        userRepositoryPort.save(userFoud);
        //TODO: create pdf to send to the user
    }
}
