package com.snglr.precios.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TarifaResult {
    private final Long productId;
    private final Long brandId;
    private final Integer priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final BigDecimal price;
    private final String curr;
}
