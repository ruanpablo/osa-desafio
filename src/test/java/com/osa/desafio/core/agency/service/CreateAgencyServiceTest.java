package com.osa.desafio.core.agency.service;

import com.osa.desafio.core.agency.mapper.AgencyMapper;
import com.osa.desafio.core.agency.model.AgencyModel;
import com.osa.desafio.core.agency.repository.AgencyRepository;
import com.osa.desafio.core.agency.request.CreateAgencyRequest;
import com.osa.desafio.core.agency.response.CreateAgencyResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class CreateAgencyServiceTest {

    private AgencyMapper agencyMapper = Mappers.getMapper(AgencyMapper.class);

    @Mock
    private AgencyRepository agencyRepository;

    @InjectMocks
    private CreateAgencyService createAgencyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.createAgencyService = new CreateAgencyService(agencyMapper, agencyRepository);
    }

    @DisplayName("Deve criar agência com sucesso")
    @Test
    void createAgencySuccess() {
        //arrange
        CreateAgencyRequest agencyOne =
                new CreateAgencyRequest("Agência Teste 1", new BigDecimal(10), new BigDecimal(8));

        AgencyModel savedModel = new AgencyModel(UUID.fromString("539fb9a9-b8a5-4c31-9127-4d5d8c8700ba"),
                "Agência Teste 1", new BigDecimal(10), new BigDecimal(8));

        //when
        Mockito.when(agencyRepository.save(any(AgencyModel.class))).thenReturn(savedModel);

        //act
        CreateAgencyResponse agencyResponse = createAgencyService.createAgency(agencyOne);

        //assert
        Assertions.assertEquals(agencyOne.name(), agencyResponse.name());
        Assertions.assertEquals(agencyOne.latitude(), agencyResponse.latitude());
        Mockito.verify(agencyRepository, Mockito.times(1)).save(any(AgencyModel.class));
    }
}