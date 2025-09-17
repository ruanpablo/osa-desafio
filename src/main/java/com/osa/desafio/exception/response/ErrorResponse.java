package com.osa.desafio.exception.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private LocalDateTime dateTime;
    private int status;
    private String errorTitle;
    private String errorMessage;
    private String exception;
    private String path;
}
