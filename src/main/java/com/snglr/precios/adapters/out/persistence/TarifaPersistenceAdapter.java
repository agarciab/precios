package com.snglr.precios.adapters.out.persistence;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.snglr.precios.domain.model.Tarifa;
import com.snglr.precios.domain.repository.TarifaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TarifaPersistenceAdapter implements TarifaRepository {

    private final TarifaJpaRepository tarifaJpaRepository;
    private final TarifaMapper tarifaMapper;

    @Override
    public Optional<Tarifa> findTarifa(LocalDateTime applicationDate, Long productId, Long brandId) {
        return tarifaJpaRepository
                .findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
                    productId, 
                    brandId, 
                    applicationDate, 
                    applicationDate)
                .map(tarifaMapper::toDomain);
    }
}
