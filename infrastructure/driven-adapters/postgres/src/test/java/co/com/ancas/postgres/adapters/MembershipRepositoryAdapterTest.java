package co.com.ancas.postgres.adapters;

import co.com.ancas.postgres.entities.MembershipEntity;
import co.com.ancas.postgres.repositories.MembershipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MembershipRepositoryAdapterTest {

    @Mock
    private MembershipRepository membershipRepository;
    @InjectMocks
    private MembershipRepositoryAdapter membershipRepositoryAdapter;
    private MembershipEntity membershipEntity;

    @BeforeEach
    void setUp() {
        membershipEntity = new MembershipEntity();
    }

    @Test
    void findIdMembershipByName() {
        //Arrange
        when(membershipRepository.findIdMembershipByName(anyString())).thenReturn(1L);
        //Act
        Long idMembership = membershipRepositoryAdapter.findIdMembershipByName("membershipName");
        //Assert
        assertNotNull(idMembership);
    }

    @Test
    void findMembershipById() {
        //Arrange
        when(membershipRepository.findById(1L)).thenReturn(java.util.Optional.of(membershipEntity));
        //Act
        var membership = membershipRepositoryAdapter.findMembershipById(1L);
        //Assert
        assertNotNull(membership);
    }
}