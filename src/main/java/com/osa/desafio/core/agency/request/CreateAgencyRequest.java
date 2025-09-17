package com.osa.desafio.core.agency.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAgencyRequest(
        @JsonProperty("agencia")
        String name,

        @JsonProperty("posX")
        @NotNull(message = "posX não pode ser nulo")
        BigDecimal latitude,

        @JsonProperty("posY")
        @NotNull(message = "posY não pode ser nulo")
        BigDecimal longitude) {
}
