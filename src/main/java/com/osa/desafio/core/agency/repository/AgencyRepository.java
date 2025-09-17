package com.osa.desafio.core.agency.repository;

import com.osa.desafio.core.agency.model.AgencyModel;
import com.osa.desafio.core.agency.response.FindNearestAgencyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgencyRepository extends JpaRepository<AgencyModel, UUID> {

    @Query(value = """
        SELECT a.name, a.latitude, a.longitude,
               CAST(SQRT(POWER(a.latitude - :latitude, 2) + POWER(a.longitude - :longitude, 2)) AS DECIMAL(9,6)) AS distance
        FROM tbl_agencies a
        ORDER BY distance ASC
        """, nativeQuery = true)
    Page<FindNearestAgencyResponse> findNearestAgencies(Long latitude, Long longitude, PageRequest pageRequest);
}
