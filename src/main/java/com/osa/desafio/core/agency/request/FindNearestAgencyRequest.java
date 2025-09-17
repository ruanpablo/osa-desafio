package com.osa.desafio.core.agency.request;

public record FindNearestAgencyRequest(
        Long latitude,
        Long longitude,
        int page,
        int size
) {

}
