package co.com.ancas.uses_cases.user;

import co.com.ancas.models.enums.Constants;
import co.com.ancas.models.enums.Messages;
import co.com.ancas.models.exceptions.BadRequestException;
import co.com.ancas.models.model.Email;
import co.com.ancas.models.model.Membership;
import co.com.ancas.models.model.User;
import co.com.ancas.models.repositories.EmailRepositoryPort;
import co.com.ancas.models.repositories.UserRepositoryPort;
import co.com.ancas.uses_cases.email_template.FindEmailTemplateBySubjectAdapter;
import co.com.ancas.uses_cases.interfaces.IUseCaseVoid;
import co.com.ancas.uses_cases.membership.FindIdMembershipByNameAdapter;
import co.com.ancas.uses_cases.membership.FindMembershipByIdAdapter;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

import static co.com.ancas.models.enums.Constants.*;


@Slf4j
@Component
@RequiredArgsConstructor
public class UpdateUserMembershipAdapter implements IUseCaseVoid<String> {
    private final FindEmailTemplateBySubjectAdapter findEmailTemplateBySubjectAdapter;
    private final FindIdMembershipByNameAdapter findIdMembershipByNameAdapter;
    private final FindMembershipByIdAdapter findMembershipByIdAdapter;
    private final FindUserFindDniAdapter findUserFindDniAdapter;
    private final EmailRepositoryPort emailRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public void execute(String s) throws MessagingException, IOException {
        User userFoud=findUserFindDniAdapter.execute(s);
        Membership membershipUserFound= findMembershipByIdAdapter.execute(userFoud.getMembershipId());
        if(membershipUserFound.getMembershipType().equals(EMPLOYEE.getConstant()) || membershipUserFound.getMembershipType().equals(PREMIUM.getConstant())){
            log.error("The user cannot update the membership {}", membershipUserFound);
            throw new BadRequestException(Messages.MESSAGE_ERROR_UPDATE_MEMBERSHIP.getMessage());
        }
        boolean isEmployee=this.userRepositoryPort.verifyEmployee(userFoud.getId());
        Constants membershipType=isEmployee?EMPLOYEE:PREMIUM;
        Long idMembership=findIdMembershipByNameAdapter.execute(membershipType.getConstant());
        userFoud.setMembershipId(idMembership);
        userRepositoryPort.save(userFoud);
        String emailTemplate=findEmailTemplateBySubjectAdapter.execute(MEMBERSHIP.getConstant());
        emailTemplate=emailTemplate.replace(":user",userFoud.getUsername());
        emailTemplate=emailTemplate.replace(":membership",membershipType.getConstant());
        String emailFound=this.userRepositoryPort.findEmailByUser(userFoud.getUsername());
        emailRepositoryPort.sendEmail(
                Email.builder()
                        .recipient(List.of(emailFound))
                        .subject(SUBJECT_MEMBERSHIP.getConstant())
                        .body(emailTemplate)
                        .build()
        );
    }
}
