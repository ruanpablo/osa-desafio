package com.osa.desafio.core.agency.response;

import java.util.List;

public record PaginationResponse<T>(
        int pageNumber,
        int pageSize,
        int totalPages,
        long totalElements,
        int count,
        boolean isLast,
        List<T> content
) {

}

