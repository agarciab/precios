package com.snglr.precios.adapters.in.rest;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.snglr.precios.adapters.in.rest.mapper.TarifaResponseMapper;
import com.snglr.precios.application.dto.TarifaResult;
import com.snglr.precios.application.ports.in.ConsultarTarifaUseCase;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controlador REST para la consulta de tarifas.
 * <p>
 * Este controlador expone el endpoint para consultar la tarifa aplicable
 * basado en una fecha, el identificador del producto y el brand (cadena).
 * </p>
 */
@Tag(name = "Tarifas", description = "Operaciones relacionadas con la consulta de tarifas")
@RestController
@RequestMapping("/tarifas")
@RequiredArgsConstructor
public class TarifaController {

	private final ConsultarTarifaUseCase consultarTarifaUseCase;
    private final TarifaResponseMapper tarifaResponseMapper;

    /**
     * Consulta la tarifa aplicable para la fecha, producto y brand indicados.
     *
     * @param applicationDate la fecha y hora de aplicación en formato ISO (ej. 2020-06-14T10:00:00)
     * @param productId       el identificador del producto
     * @param brandId         el identificador de la marca (brand)
     * @return un objeto {@link TarifaResponse} con la información de la tarifa
     */
    @Operation(summary = "Consultar Tarifa", description = "Consulta la tarifa aplicable basada en la fecha, producto y cadena", security = @SecurityRequirement(name = "oauth2"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarifa encontrada"),
        @ApiResponse(responseCode = "404", description = "Tarifa no encontrada",  content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida",  content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
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
