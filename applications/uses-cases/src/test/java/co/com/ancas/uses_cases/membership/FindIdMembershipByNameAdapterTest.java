package co.com.ancas.uses_cases.membership;

import co.com.ancas.models.repositories.MembershipRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindIdMembershipByNameAdapterTest {

    @Mock
    private MembershipRepositoryPort membershipRepositoryPort;
    @InjectMocks
    private FindIdMembershipByNameAdapter findIdMembershipByNameAdapter;

    @Test
    void execute() {
        //Arrange
        when(membershipRepositoryPort.findIdMembershipByName(anyString())).thenReturn(1L);
        //Act
        Long id = findIdMembershipByNameAdapter.execute("Membership");
        //Assert
        assertNotNull(id);
    }

    @Test
    void executeWhenIdIsNull() {
        //Arrange
        when(membershipRepositoryPort.findIdMembershipByName(anyString())).thenReturn(null);
        //Act
        assertThrows(NullPointerException.class, () -> findIdMembershipByNameAdapter.execute("Membership"));
    }
}