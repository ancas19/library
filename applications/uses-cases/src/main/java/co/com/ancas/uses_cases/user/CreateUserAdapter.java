package co.com.ancas.uses_cases.user;

import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.User;
import co.com.ancas.models.model.UserCreation;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.EmailTemplateRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import co.com.ancas.uses_cases.membership.FindIdMembershipByNameAdapter;
import co.com.ancas.uses_cases.roles.FindIdRoleByNameAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

import static co.com.ancas.models.enums.Constants.*;

@RequiredArgsConstructor
@Component
public class CreateUserAdapter implements IUseCaseVoid<UserCreation> {
    private final FindIdRoleByNameAdapter findIdRoleByNameAdapter;
    private final FindIdMembershipByNameAdapter findIdMembershipByNameAdapter;
    private final UserRepositoryPort userRepositoryport;
    private final EmailTemplateRepositoryPort emailTemplateRepositoryPort;
    private final EmailRepositoryPort emailRepositoryPort;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void execute(UserCreation userCreation) throws MessagingException {
        String userName=createUserName(userCreation);
        String membership=userCreation.getUserType().equals(USER.getConstant())?NORMAL.getConstant():EMPLOYEE.getConstant();
        String role=userCreation.getUserType().equals(USER.getConstant())?USER.getConstant():EMPLOYEE.getConstant();
        String password=createPassword(12);
        this.userRepositoryport.save(
                User.builder()
                        .personId(userCreation.getId())
                        .username(userName.toString())
                        .password(passwordEncoder.encode(password))
                        .roleId(this.findIdRoleByNameAdapter.execute(role))
                        .membershipId(this.findIdMembershipByNameAdapter.execute(membership))
                        .emailVerified(false)
                        .changePassword(true)
                        .build()
        );
        String templateFound=emailTemplateRepositoryPort.findEmailTemplateBySubject(USER_AND_PASSWORD.getConstant());
        templateFound=templateFound.replace(":name",userCreation.getFirstName());
        templateFound=templateFound.replace(":username",userName);
        templateFound=templateFound.replace(":password",password);
        this.emailRepositoryPort.sendEmail(
                Email.builder()
                        .recipient(userCreation.getEmail())
                        .subject(SUBJECT_USER_AND_PASSWORD.getConstant())
                        .body(templateFound)
                        .build()
        );
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
