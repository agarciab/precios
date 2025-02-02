package com.snglr.precios.adapters.in.rest.mapper;
import org.mapstruct.Mapper;

import com.snglr.precios.adapters.in.rest.TarifaResponse;
import com.snglr.precios.application.dto.TarifaResult;

@Mapper(componentModel = "spring")
public interface TarifaResponseMapper {
    TarifaResponse toTarifaResponse(TarifaResult result);
}
