package com.snglr.precios.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.snglr.precios.domain.exception.TarifaNotFoundException;
import com.snglr.precios.domain.model.Tarifa;
import com.snglr.precios.domain.repository.TarifaRepository;

@Tag("unit")
class ConsultarTarifaServiceTest {

    private TarifaRepository tarifaRepository;
    private ConsultarTarifaService consultarTarifaService;

    @BeforeEach
    void setUp() {
        tarifaRepository = mock(TarifaRepository.class);
        consultarTarifaService = new ConsultarTarifaService(tarifaRepository);
    }

    /**
     * Verifica que, cuando el repositorio devuelve una tarifa,
     * el método execute la retorna correctamente.
     */
    @Test
    void execute_shouldReturnTarifa_whenTarifaExists() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;
        Tarifa tarifa = new Tarifa(
                brandId,
                LocalDateTime.of(2020, 6, 14, 0, 0),
                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                1,
                productId,
                new BigDecimal("35.50"),
                "EUR"
        );

        when(tarifaRepository.findTarifa(applicationDate, productId, brandId))
                .thenReturn(Optional.of(tarifa));

        Tarifa result = consultarTarifaService.execute(applicationDate, productId, brandId);

        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals(brandId, result.getBrandId());
        assertEquals(1, result.getPriceList());
        assertEquals(new BigDecimal("35.50"), result.getPrice());
        assertEquals("EUR", result.getCurr());
    }

    /**
     * Verifica que, cuando no se encuentra tarifa, se lance una excepción de dominio.
     */
    @Test
    void execute_shouldThrowException_whenTarifaNotFound() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 12, 21, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(tarifaRepository.findTarifa(applicationDate, productId, brandId))
                .thenReturn(Optional.empty());

        assertThrows(TarifaNotFoundException.class, () -> {
            consultarTarifaService.execute(applicationDate, productId, brandId);
        });
    }
}
