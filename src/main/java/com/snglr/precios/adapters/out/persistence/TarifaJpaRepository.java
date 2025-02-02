package com.snglr.precios.adapters.out.persistence;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TarifaJpaRepository extends JpaRepository<TarifaEntity, Long> {

	Optional<TarifaEntity> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
	        Long productId,
	        Long brandId,
	        LocalDateTime applicationDate1,
	        LocalDateTime applicationDate2
	    );
}


