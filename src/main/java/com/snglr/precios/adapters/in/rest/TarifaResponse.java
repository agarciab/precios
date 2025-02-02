package com.snglr.precios.adapters.in.rest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class TarifaResponse {
    private final Long productId;
    private final Long brandId;
    private final Integer priceList;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final BigDecimal price;
    private final String curr;
}
