package co.com.ancas.uses_cases.roles;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.repositories.RolesRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindIdRoleByNameAdapterTest {
    @Mock
    private RolesRepositoryPort rolesRepositoryPort;
    @InjectMocks
    private FindIdRoleByNameAdapter findIdRoleByNameAdapter;

    @Test
    void execute() {
        //Arrange
        when(rolesRepositoryPort.findIdRoleByName(anyString())).thenReturn(1L);
        //Act
        Long result = findIdRoleByNameAdapter.execute("role");
        //Assert
        assertNotNull(result);
    }

    @Test
    void executeRoleNotFound() {
        //Arrange
        when(rolesRepositoryPort.findIdRoleByName(anyString())).thenReturn(null);
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findIdRoleByNameAdapter.execute("role"));
    }
}