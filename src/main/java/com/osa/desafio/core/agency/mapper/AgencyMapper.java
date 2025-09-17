package com.osa.desafio.core.agency.mapper;

import com.osa.desafio.core.agency.model.AgencyModel;
import com.osa.desafio.core.agency.request.CreateAgencyRequest;
import com.osa.desafio.core.agency.response.CreateAgencyResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AgencyMapper {

    AgencyModel toModel(CreateAgencyRequest createAgencyRequest);

    CreateAgencyResponse toResponse(AgencyModel save);
}
