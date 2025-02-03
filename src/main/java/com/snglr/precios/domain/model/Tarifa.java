package com.snglr.precios.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Representa la tarifa de un producto para una marca en un rango de fechas.
 * <p>
 * La clase Tarifa es el modelo de dominio que contiene la información necesaria
 * para determinar el precio aplicable.
 * </p>
 */
@Getter
@AllArgsConstructor
@ToString
public class Tarifa {
    private final Long brandId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final Integer priceList;
    private final Long productId;
    private final Integer priority;
    private final BigDecimal price;
    private final String curr;

    /**
     * Verifica si la tarifa es aplicable para la fecha indicada.
     *
     * @param applicationDate la fecha de aplicación
     * @return {@code true} si la fecha está dentro del rango de la tarifa, de lo contrario {@code false}
     */
    public boolean isApplicable(LocalDateTime applicationDate) {
        return (applicationDate.isEqual(startDate) || applicationDate.isAfter(startDate))
                && (applicationDate.isEqual(endDate) || applicationDate.isBefore(endDate));
    }
}
