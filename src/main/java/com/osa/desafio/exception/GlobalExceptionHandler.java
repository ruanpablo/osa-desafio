package com.osa.desafio.exception;

import com.osa.desafio.exception.custom.ResourceAlreadyExistsException;
import com.osa.desafio.exception.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestController
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    final HttpServletRequest servletRequest;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("method=handleException exception={} message={}", exception.getClass(), exception.getMessage(), exception);
        ErrorResponse responseBody = new ErrorResponse(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno do Servidor", exception.getMessage(), exception.getClass().getSimpleName(), servletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("; "));
        ErrorResponse responseBody = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                "Campos Inválidos", message, ex.getClass().getSimpleName(), servletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsRuntimeException(ResourceAlreadyExistsException ex) {
        ErrorResponse responseBody = new ErrorResponse(LocalDateTime.now(), HttpStatus.CONFLICT.value(),
                "Recurso já existe.", ex.getMessage(), ex.getClass().getSimpleName(), servletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }

}
