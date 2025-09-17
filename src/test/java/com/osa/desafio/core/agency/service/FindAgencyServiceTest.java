package com.osa.desafio.core.agency.service;

import com.osa.desafio.core.agency.model.AgencyModel;
import com.osa.desafio.core.agency.repository.AgencyRepository;
import com.osa.desafio.core.agency.request.FindNearestAgencyRequest;
import com.osa.desafio.core.agency.response.FindNearestAgencyResponse;
import com.osa.desafio.core.agency.response.PaginationResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // H2 em memória
@Transactional
class FindAgencyServiceTest {

    @Autowired
    private FindAgencyService findAgencyService;

    @Autowired
    private AgencyRepository agencyRepository;

    @BeforeEach
    void setUp() {
        agencyRepository.deleteAll();

        agencyRepository.save(new AgencyModel(null, "Agência 1", new BigDecimal("-23.550000"), new BigDecimal("-46.630000")));
        agencyRepository.save(new AgencyModel(null, "Agência 2", new BigDecimal("-23.560000"), new BigDecimal("-46.640000")));
        agencyRepository.save(new AgencyModel(null, "Agência 3", new BigDecimal("-23.570000"), new BigDecimal("-46.650000")));
    }

    @DisplayName("Deve retornar agências próximas com paginação")
    @Test
    void shouldReturnNearestAgenciesWithPagination() {
        // arrange
        FindNearestAgencyRequest request = new FindNearestAgencyRequest(new BigDecimal("-23.554000"), new BigDecimal("-46.634000"), 0, 2);

        // act
        PaginationResponse<FindNearestAgencyResponse> response = findAgencyService.findNearestAgencies(request);

        // assert
        assertThat(response).isNotNull();
        assertThat(response.pageNumber()).isEqualTo(0);
        assertThat(response.pageSize()).isEqualTo(2);
        assertThat(response.content().size()).isEqualTo(2); // tamanho da página
        assertThat(response.totalElements()).isEqualTo(3);  // total de agências cadastradas
        assertThat(response.totalPages()).isEqualTo(2);     // 3 agências / 2 por página = 2 páginas
        assertThat(response.isLast()).isFalse();
        assertThat(response.content().get(0).name()).isEqualTo("Agência 1"); // primeira agência mais próxima
    }
}