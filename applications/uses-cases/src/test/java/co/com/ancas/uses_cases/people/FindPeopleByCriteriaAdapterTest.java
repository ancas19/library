package co.com.ancas.uses_cases.people;

import co.com.ancas.models.exceptions.NotFoundException;
import co.com.ancas.models.model.People;
import co.com.ancas.models.model.PeopleSearchCriteria;
import co.com.ancas.models.repositories.PeopleRepositoryPort;
import co.com.ancas.models.utils.TestMock;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindPeopleByCriteriaAdapterTest {
    @Mock
    private PeopleRepositoryPort peopleRepositoryPort;
    @InjectMocks
    private FindPeopleByCriteriaAdapter findPeopleByCriteriaAdapter;
    private People people;
    private PeopleSearchCriteria peopleSearchCriteria;

    @BeforeEach
    void setUp() {
        people = TestMock.people();
        peopleSearchCriteria = TestMock.peopleSearchCriteria();
    }

    @Test
    void execute() throws MessagingException {
        //Arrange
        when(peopleRepositoryPort.findPeopleByCriteria(peopleSearchCriteria)).thenReturn(new PageImpl<>(List.of(people)));
        //Act
        Page<People> peopleFound = findPeopleByCriteriaAdapter.execute(peopleSearchCriteria);
        //Assert
        assertNotNull(peopleFound);
    }

    @Test
    void executeNotFound() {
        //Arrange
        when(peopleRepositoryPort.findPeopleByCriteria(peopleSearchCriteria)).thenReturn(Page.empty());
        //Act and Assert
        assertThrows(NotFoundException.class, () -> findPeopleByCriteriaAdapter.execute(peopleSearchCriteria));
    }
}