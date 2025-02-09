package com.snglr.precios.domain.model;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class TarifaTest {

    private final Long brandId = 1L;
    private final Long productId = 35455L;
    private final Integer priceList = 1;
    private final BigDecimal price = new BigDecimal("35.50");
    private final String curr = "EUR";

    private final LocalDateTime startDate = LocalDateTime.of(2020, 6, 14, 0, 0);
    private final LocalDateTime endDate = LocalDateTime.of(2020, 12, 31, 23, 59, 59);

    /**
     * Test cuando la fecha es igual a startDate.
     */
    @Test
    void isApplicable_returnsTrue_whenDateEqualsStartDate() {
        Tarifa tarifa = new Tarifa(brandId, startDate, endDate, priceList, productId, price, curr);
        assertTrue(tarifa.isApplicable(startDate));
    }

    /**
     * Test cuando la fecha es igual a endDate.
     */
    @Test
    void isApplicable_returnsTrue_whenDateEqualsEndDate() {
        Tarifa tarifa = new Tarifa(brandId, startDate, endDate, priceList, productId, price, curr);
        assertTrue(tarifa.isApplicable(endDate));
    }

    /**
     * Test cuando la fecha es intermedia al rango (caso normal).
     */
    @Test
    void isApplicable_returnsTrue_whenDateIsWithinRange() {
        Tarifa tarifa = new Tarifa(brandId, startDate, endDate, priceList, productId, price, curr);
        LocalDateTime midDate = LocalDateTime.of(2020, 8, 1, 12, 0);
        assertTrue(tarifa.isApplicable(midDate));
    }

    /**
     * Test cuando la fecha es anterior al startDate.
     */
    @Test
    void isApplicable_returnsFalse_whenDateIsBeforeStartDate() {
        Tarifa tarifa = new Tarifa(brandId, startDate, endDate, priceList, productId, price, curr);
        LocalDateTime beforeDate = startDate.minusMinutes(1);
        assertFalse(tarifa.isApplicable(beforeDate));
    }

    /**
     * Test cuando la fecha es posterior al endDate.
     */
    @Test
    void isApplicable_returnsFalse_whenDateIsAfterEndDate() {
        Tarifa tarifa = new Tarifa(brandId, startDate, endDate, priceList, productId, price, curr);
        LocalDateTime afterDate = endDate.plusMinutes(1);
        assertFalse(tarifa.isApplicable(afterDate));
    }
}
