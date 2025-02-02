package co.com.ancas.uses_cases.membership;

import co.com.ancas.models.model.Membership;
import co.com.ancas.models.repositories.MembershipRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindMembershipByIdAdapterTest {

    @Mock
    private MembershipRepositoryPort membershipRepositoryPort;
    @InjectMocks
    private FindMembershipByIdAdapter findMembershipByIdAdapter;
    private Membership membership;

    @BeforeEach
    void setUp(){
        membership = TestMock.membership();
    }

    @Test
    void execute() {
        //Arrange
        when(membershipRepositoryPort.findMembershipById(1L)).thenReturn(Optional.of(membership));
        //Act
        Membership membershipFound = findMembershipByIdAdapter.execute(1L);
        //Assert
        assertNotNull(membershipFound);
    }

    @Test
    void executeWhenMembershipIsNull() {
        //Arrange
        when(membershipRepositoryPort.findMembershipById(1L)).thenReturn(Optional.empty());
        //Act
        assertThrows(NullPointerException.class, () -> findMembershipByIdAdapter.execute(1L));
    }
}