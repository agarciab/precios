package com.snglr.precios.adapters.in.rest;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snglr.precios.adapters.in.rest.mapper.TarifaResponseMapper;
import com.snglr.precios.application.dto.TarifaResult;
import com.snglr.precios.application.ports.in.ConsultarTarifaUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tarifas")
@RequiredArgsConstructor
public class TarifaController {

	private final ConsultarTarifaUseCase consultarTarifaUseCase;
    private final TarifaResponseMapper tarifaResponseMapper;

    @GetMapping
    public TarifaResponse getTarifa(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId
    ) {
        TarifaResult result = consultarTarifaUseCase.consultarTarifa(applicationDate, productId, brandId);
        return tarifaResponseMapper.toTarifaResponse(result);
    }
}
