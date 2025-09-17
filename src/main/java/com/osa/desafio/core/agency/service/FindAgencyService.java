package com.osa.desafio.core.agency.service;

import com.osa.desafio.core.agency.repository.AgencyRepository;
import com.osa.desafio.core.agency.request.FindNearestAgencyRequest;
import com.osa.desafio.core.agency.response.FindNearestAgencyResponse;
import com.osa.desafio.core.agency.response.PaginationResponse;
import com.osa.desafio.usecase.agency.FindAgencyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class FindAgencyService implements FindAgencyUseCase<FindNearestAgencyResponse> {

    final AgencyRepository agencyRepository;

    @Override
    public PaginationResponse<FindNearestAgencyResponse> findNearestAgencies(FindNearestAgencyRequest request) {
        log.info("stage=init method=findNearestAgencies request={}", request);
        PageRequest pageRequest = PageRequest.of(request.page(), request.size());
        Page<FindNearestAgencyResponse> page = agencyRepository.findNearestAgencies(request.latitude(), request.longitude(), pageRequest);

        PaginationResponse<FindNearestAgencyResponse> paginationResponse = new PaginationResponse<>(request.page(),
                request.size(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getContent().size(),
                page.isLast(),
                page.getContent());

        log.info("stage=end method=findNearestAgencies pageNumber={} pageSize={} totalPages={}", request.page(), request.size(), paginationResponse.getTotalPages());
        return paginationResponse;
    }
}
