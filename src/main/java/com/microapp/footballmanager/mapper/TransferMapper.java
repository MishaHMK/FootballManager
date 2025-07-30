package com.microapp.footballmanager.mapper;

import com.microapp.footballmanager.dto.transfer.TransferDto;
import com.microapp.footballmanager.model.Transfer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    @Mapping(target = "playerId", source = "player.id")
    @Mapping(target = "fromTeamId", source = "fromTeam.id")
    @Mapping(target = "toTeamId", source = "toTeam.id")
    @Mapping(target = "status", source = "status")
    TransferDto toDto(Transfer transfer);
}
