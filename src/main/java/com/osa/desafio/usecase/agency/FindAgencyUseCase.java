package com.osa.desafio.usecase.agency;

import com.osa.desafio.core.agency.request.FindNearestAgencyRequest;
import com.osa.desafio.core.agency.response.PaginationResponse;

public interface FindAgencyUseCase<T> {
    PaginationResponse<T> findNearestAgencies(FindNearestAgencyRequest request);
}
