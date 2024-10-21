package co.com.ancas.uses_cases.user;

import co.com.ancas.models.model.People;
import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserCreation;
import co.com.ancas.models.repositories.UserRepositoryport;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import co.com.ancas.uses_cases.membership.FindIdMembershipByNameAdapter;
import co.com.ancas.uses_cases.roles.FindIdRoleByNameAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

import static co.com.ancas.models.enums.Constants.*;

@RequiredArgsConstructor
@Component
public class CreateUserAdapter implements IUseCaseVoid<UserCreation> {
    private final FindIdRoleByNameAdapter findIdRoleByNameAdapter;
    private final FindIdMembershipByNameAdapter findIdMembershipByNameAdapter;
    private final UserRepositoryport userRepositoryport;

    @Override
    public void execute(UserCreation userCreation) {
        String userName=createUserName(userCreation);
        String membership=userCreation.getUserType().equals(USER.getConstant())?NORMAL.getConstant():EMPLOYEE.getConstant();
        String role=userCreation.getUserType().equals(USER.getConstant())?USER.getConstant():EMPLOYEE.getConstant();
        this.userRepositoryport.save(
                User.builder()
                        .personId(userCreation.getId())
                        .username(userName.toString())
                        .password(createPassword(12))
                        .roleId(this.findIdRoleByNameAdapter.execute(role))
                        .membershipId(this.findIdMembershipByNameAdapter.execute(membership))
                        .emailVerified(false)
                        .changePassword(true)
                        .build()
        );
        //TODO:Send email.
    }

    private String createPassword(Integer length) {
        String characters = CHARACTERS.getConstant();
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    private String createUserName(UserCreation people) {
        Integer contador=1;
        String userName;
        do{
            userName="%s.%s%s".formatted(people.getFirstName().split(" ")[0],people.getLastName().split(" ")[0],contador);
        }while (userRepositoryport.verifyExistsUserName(userName));
        return userName;
    }

}
