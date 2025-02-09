package co.com.ancas.utils;

import co.com.ancas.models.model.LoanInformation;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.response.LoanInformationResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaginationTest {

    @InjectMocks
    private Pagination pagination;
    private LoanInformation loanInformation;

    @BeforeEach
    void setUp() {
        loanInformation= TestMock.loanInformation();
    }

    @Test
    void getPaginationResponse() {
        //Arrange
        Page<LoanInformation> page=new PageImpl<>(List.of(loanInformation));
        //Act and Assert
        assertNotNull(pagination.getPaginationResponse(page, LoanInformationResponse.class));
    }
}