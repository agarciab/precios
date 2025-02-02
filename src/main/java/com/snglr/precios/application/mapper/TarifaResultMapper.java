package com.snglr.precios.application.mapper;
import org.mapstruct.Mapper;

import com.snglr.precios.application.dto.TarifaResult;
import com.snglr.precios.domain.model.Tarifa;

@Mapper(componentModel = "spring")
public interface TarifaResultMapper {
    TarifaResult toTarifaResult(Tarifa domain);
}

