package com.osa.desafio.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.osa.desafio.core.agency.request.CreateAgencyRequest;
import com.osa.desafio.core.agency.request.FindNearestAgencyRequest;
import com.osa.desafio.core.agency.response.CreateAgencyResponse;
import com.osa.desafio.core.agency.response.FindNearestAgencyResponse;
import com.osa.desafio.core.agency.response.PaginationResponse;
import com.osa.desafio.usecase.agency.CreateAgencyUseCase;
import com.osa.desafio.usecase.agency.FindAgencyUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AgencyControllerTest {

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private CreateAgencyUseCase createAgencyUseCase;

    @Mock
    private FindAgencyUseCase findAgencyUseCase;

    @InjectMocks
    private AgencyController agencyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(agencyController).build();
    }

    @DisplayName("Deve criar uma agência com sucesso")
    @Test
    void createAgencySuccess() throws Exception {
        CreateAgencyRequest request = new CreateAgencyRequest("Agência Teste", new BigDecimal("10"), new BigDecimal("20"));
        CreateAgencyResponse response = new CreateAgencyResponse("Agência Teste", new BigDecimal("10"), new BigDecimal("20"));

        Mockito.when(createAgencyUseCase.createAgency(any(CreateAgencyRequest.class))).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.agencia").value("Agência Teste"))
                .andExpect(jsonPath("$.posX").value(10))
                .andExpect(jsonPath("$.posY").value(20));
    }

    @DisplayName("Deve buscar agências próximas com sucesso")
    @Test
    void findDistanceAgencyToUserSuccess() throws Exception {
        FindNearestAgencyResponse agency1 = new FindNearestAgencyResponse("Agência 1", new BigDecimal("10"), new BigDecimal("20"), new BigDecimal("1.0"));
        PaginationResponse<FindNearestAgencyResponse> pagination = new PaginationResponse<>(0, 5, 1, 1, 1, true, List.of(agency1));

        Mockito.when(findAgencyUseCase.findNearestAgencies(any(FindNearestAgencyRequest.class)))
                .thenReturn(pagination);

        // act & assert
        mockMvc.perform(MockMvcRequestBuilders.get("/distancia")
                        .param("posX", "10")
                        .param("posY", "20")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].agencia").value("Agência 1"))
                .andExpect(jsonPath("$.content[0].posX").value(10))
                .andExpect(jsonPath("$.content[0].posY").value(20));
    }
}