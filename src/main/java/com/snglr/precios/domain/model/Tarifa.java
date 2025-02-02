package com.snglr.precios.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

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
     * Indica si la tarifa es aplicable para una fecha dada.
     */
    public boolean isApplicable(LocalDateTime applicationDate) {
        return (applicationDate.isEqual(startDate) || applicationDate.isAfter(startDate))
                && (applicationDate.isEqual(endDate) || applicationDate.isBefore(endDate));
    }
}
