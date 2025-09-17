package com.osa.desafio.core.agency.request;

import java.math.BigDecimal;

public record FindNearestAgencyRequest(
        BigDecimal latitude,
        BigDecimal longitude,
        int page,
        int size
) {

}
