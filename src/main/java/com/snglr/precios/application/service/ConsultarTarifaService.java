package com.snglr.precios.application.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.snglr.precios.application.dto.TarifaResult;
import com.snglr.precios.application.mapper.TarifaResultMapper;
import com.snglr.precios.application.ports.in.ConsultarTarifaUseCase;
import com.snglr.precios.domain.repository.TarifaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultarTarifaService implements ConsultarTarifaUseCase {

    private final TarifaRepository tarifaRepository;
    private final TarifaResultMapper tarifaResultMapper;

    @Override
    public TarifaResult consultarTarifa(LocalDateTime applicationDate, Long productId, Long brandId) {
        return tarifaRepository.findTarifa(applicationDate, productId, brandId)
                .map(tarifaResultMapper::toTarifaResult)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No se encontró tarifa para los criterios indicados"));
    }
}
