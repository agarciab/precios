package com.snglr.precios.application.ports.in;

import java.time.LocalDateTime;

import com.snglr.precios.domain.model.Tarifa;

public interface ConsultarTarifaUseCase {
    /**
     * Consulta la tarifa aplicable dada una fecha de aplicación, producto y cadena.
     */
    Tarifa consultarTarifa(LocalDateTime applicationDate, Long productId, Long brandId);
}
