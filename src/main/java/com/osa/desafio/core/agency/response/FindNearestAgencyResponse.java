package com.osa.desafio.core.agency.response;

import java.math.BigDecimal;

public record FindNearestAgencyResponse(String name,
                                        BigDecimal latitude,
                                        BigDecimal longitude,
                                        BigDecimal distance) {

}
