package com.osa.desafio.core.agency.service;

import com.osa.desafio.core.agency.mapper.AgencyMapper;
import com.osa.desafio.core.agency.model.AgencyModel;
import com.osa.desafio.core.agency.repository.AgencyRepository;
import com.osa.desafio.core.agency.request.CreateAgencyRequest;
import com.osa.desafio.core.agency.response.CreateAgencyResponse;
import com.osa.desafio.exception.custom.ResourceAlreadyExistsException;
import com.osa.desafio.usecase.agency.CreateAgencyUseCase;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.osa.desafio.core.agency.enums.AgencyMessageErrorEnum.DUPLICATE_LATITUDE_LONGITUDE;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreateAgencyService implements CreateAgencyUseCase{

    final AgencyMapper agencyMapper;

    final AgencyRepository agencyRepository;

    @Override
    public CreateAgencyResponse createAgency(CreateAgencyRequest createAgencyRequest) throws ResourceAlreadyExistsException {
        try {
            log.info("stage=init method=createAgency request={}", createAgencyRequest);

            AgencyModel agencyModel = agencyMapper.toModel(createAgencyRequest);

            AgencyModel save = agencyRepository.save(agencyModel);

            CreateAgencyResponse response = agencyMapper.toResponse(save);
            log.info("stage=init method=createAgency response={}", response);
            return response;
        } catch (DataIntegrityViolationException exception){
            throw new ResourceAlreadyExistsException(DUPLICATE_LATITUDE_LONGITUDE.getMensagem());
        }
    }
}
