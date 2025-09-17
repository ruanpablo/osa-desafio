package com.osa.desafio.usecase.agency;

import com.osa.desafio.core.agency.request.CreateAgencyRequest;
import com.osa.desafio.core.agency.response.CreateAgencyResponse;
import com.osa.desafio.exception.custom.ResourceAlreadyExistsException;

public interface CreateAgencyUseCase {
    CreateAgencyResponse createAgency(CreateAgencyRequest createAgencyRequest) throws ResourceAlreadyExistsException;
}
