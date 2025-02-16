package co.com.ancas.controllers;

import co.com.ancas.exception.CustomExceptionHandler;
import co.com.ancas.models.model.People;
import co.com.ancas.models.utils.Mapper;
import co.com.ancas.models.utils.TestMock;
import co.com.ancas.response.PeopleFullInfomrationResponse;
import co.com.ancas.response.PeopleResponse;
import co.com.ancas.service.PeopleAppService;
import co.com.ancas.utils.Pagination;
import co.com.ancas.utils.RequestMocks;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PeopleControllerTest {

    @Mock
    private PeopleAppService peopleAppservice;
    @InjectMocks
    private PeopleController peopleController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String url="/v1.0/people";

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(peopleController)
                .setControllerAdvice(new CustomExceptionHandler())
                .build();
    }

    @Test
    void findAll() throws Exception {
        //Arrange
        Page<People> peoplePage=new PageImpl<>(List.of(TestMock.people()));
        when(peopleAppservice.findAllByCriteria(any(), any(), any())).thenReturn(Pagination.getPaginationResponse(peoplePage, PeopleResponse.class));
        //Act
        ResultActions response=mockMvc.perform(post(url+"/all")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.peopleSearchCriteriaRequest())));
        //Assert
        response.andDo(print())
                .andExpect(status().isOk());

    }


    @Test
    void findById() throws Exception {
        //Arrange
        when(peopleAppservice.findById(1L)).thenReturn(Mapper.map(TestMock.peopleFullInfomration(), PeopleFullInfomrationResponse.class));
        //Act
        ResultActions response=mockMvc.perform(get(url+"/1"));
        //Assert
        response.andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void updatePeople() throws Exception {
        //Arrange
        when(peopleAppservice.updatePeople(any())).thenReturn(Mapper.map(TestMock.people(), PeopleResponse.class));
        //Act
        ResultActions response=mockMvc.perform(patch(url)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.peopleInformationRequest())));
        //Assert
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void uploadProfileImage() throws Exception {
        //Arrange
        doNothing().when(peopleAppservice).uploadProfileImage(any());
        //Act
        ResultActions response=mockMvc.perform(post(url+"/profile-image")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(RequestMocks.imageUploadRequest())));
        //Assert
        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void blockPeople() throws Exception {
        //Arrange
        doNothing().when(peopleAppservice).blockPeople(1L);
        //Act
        ResultActions response=mockMvc.perform(delete(url+"/status/1"));
        //Assert
        response.andDo(print())
                .andExpect(status().isOk());
    }
}