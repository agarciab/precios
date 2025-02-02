package com.snglr.precios.domain.repository;


import java.time.LocalDateTime;
import java.util.Optional;

import com.snglr.precios.domain.model.Tarifa;

public interface TarifaRepository {
    /**
     * Busca la tarifa aplicable para la fecha dada, el producto y la cadena (brand).
     * En caso de solapamientos se retorna la tarifa con mayor prioridad.
     */
    Optional<Tarifa> findTarifa(LocalDateTime applicationDate, Long productId, Long brandId);
}
