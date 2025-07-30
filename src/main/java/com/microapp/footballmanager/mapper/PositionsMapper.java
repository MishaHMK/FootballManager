package com.microapp.footballmanager.mapper;

import com.microapp.footballmanager.config.MapperConfig;
import com.microapp.footballmanager.dto.position.CreatePositionDto;
import com.microapp.footballmanager.dto.position.PositionDto;
import com.microapp.footballmanager.model.Position;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public interface PositionsMapper {
    PositionDto toDto(Position position);

    Position toEntity(CreatePositionDto createPositionDto);
}
