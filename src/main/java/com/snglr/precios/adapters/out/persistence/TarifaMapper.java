package com.snglr.precios.adapters.out.persistence;


import org.mapstruct.Mapper;
import com.snglr.precios.domain.model.Tarifa;

@Mapper(componentModel = "spring")
public interface TarifaMapper {
    Tarifa toDomain(TarifaEntity entity);
}

