package com.snglr.precios.application.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.snglr.precios.application.ports.in.ConsultarTarifaUseCase;
import com.snglr.precios.domain.exception.TarifaNotFoundException;
import com.snglr.precios.domain.model.Tarifa;
import com.snglr.precios.domain.repository.TarifaRepository;

import lombok.RequiredArgsConstructor;

/**
 * Servicio que implementa el caso de uso de consulta de tarifas.
 * <p>
 * Este servicio coordina la consulta a través del repositorio y mapea el
 * modelo de dominio a un DTO de aplicación.
 * </p>
 */
@Service
@RequiredArgsConstructor
public class ConsultarTarifaService implements ConsultarTarifaUseCase {

    private final TarifaRepository tarifaRepository;

    /**
     * Consulta la tarifa aplicable para la fecha, producto y brand indicados.
     *
     * @param applicationDate la fecha y hora de aplicación
     * @param productId       el identificador del producto
     * @param brandId         el identificador de la marca
     * @return un {@link Tarifa} con los datos de la tarifa
     * @throws TarifaNotFoundException si no se encuentra la tarifa
     */
    @Override
    public Tarifa consultarTarifa(LocalDateTime applicationDate, Long productId, Long brandId) {
        return tarifaRepository.findTarifa(applicationDate, productId, brandId)
                .orElseThrow(() -> new TarifaNotFoundException("No se encontró tarifa para los criterios indicados"));
    }
}
