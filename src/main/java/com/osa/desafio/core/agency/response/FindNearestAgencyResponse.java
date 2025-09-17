package com.osa.desafio.core.agency.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record FindNearestAgencyResponse(
        @JsonProperty("agencia")
        String name,
        @JsonProperty("posX")
        BigDecimal latitude,
        @JsonProperty("posY")
        BigDecimal longitude,
        @JsonProperty("distancia")
        BigDecimal distance) {

}
