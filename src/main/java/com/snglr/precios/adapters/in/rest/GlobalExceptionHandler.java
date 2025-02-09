package com.snglr.precios.adapters.in.rest;

import java.net.URI;
import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.snglr.precios.domain.exception.TarifaNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Gestor global de excepciones para la API REST.
 * Captura excepciones de dominio y las transforma en respuestas HTTP coherentes.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja la excepción TarifaNotFoundException y retorna un HTTP 404.
     *
     * @param ex la excepción lanzada
     * @return un ResponseEntity con el mensaje de error y el código HTTP 404
     */
	@ExceptionHandler(TarifaNotFoundException.class)
    public ProblemDetail handleTarifaNotFoundException(TarifaNotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
        problemDetail.setType(URI.create("https://snglr.com/docs/errors/tarifa-not-found"));
        problemDetail.setProperty("timestamp", Instant.now().toString());
        return problemDetail;
    }
}
