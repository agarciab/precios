package com.snglr.precios.application.ports.in;

import java.time.LocalDateTime;

import com.snglr.precios.application.dto.TarifaResult;

public interface ConsultarTarifaUseCase {
    /**
     * Consulta la tarifa aplicable dada una fecha de aplicación, producto y cadena.
     */
    TarifaResult consultarTarifa(LocalDateTime applicationDate, Long productId, Long brandId);
}
