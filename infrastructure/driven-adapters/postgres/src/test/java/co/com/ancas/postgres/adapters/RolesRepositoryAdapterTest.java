package co.com.ancas.postgres.adapters;

import co.com.ancas.postgres.repositories.RolesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolesRepositoryAdapterTest {
    @Mock
    private RolesRepository rolesRepository;
    @InjectMocks
    private RolesRepositoryAdapter rolesRepositoryAdapter;

    @Test
    void findIdRoleByName() {
        //Arrange
        String roleName = "roleName";
        Long id = 1L;
        when(rolesRepository.findIdRoleByName(roleName)).thenReturn(id);
        //Act
        Long result = rolesRepositoryAdapter.findIdRoleByName(roleName);
        //Assert
        assertEquals(id, result);
    }
}