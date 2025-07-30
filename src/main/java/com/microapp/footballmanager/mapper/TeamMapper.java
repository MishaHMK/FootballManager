package com.microapp.footballmanager.mapper;

import com.microapp.footballmanager.config.MapperConfig;
import com.microapp.footballmanager.dto.team.CreateTeamDto;
import com.microapp.footballmanager.dto.team.TeamDto;
import com.microapp.footballmanager.dto.team.TeamWithPlayersDto;
import com.microapp.footballmanager.dto.team.UpdateTeamDto;
import com.microapp.footballmanager.model.Player;
import com.microapp.footballmanager.model.Team;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class}, config = MapperConfig.class)
public interface TeamMapper {
    @Mapping(target = "playerIds", ignore = true)
    TeamDto toDto(Team team);

    TeamWithPlayersDto toDtoWithPlayers(Team team);

    @AfterMapping
    default void setPlayerIds(@MappingTarget TeamDto dto, Team team) {
        Set<Long> playerIds = team.getPlayers().stream()
                .map(Player::getId)
                .collect(Collectors.toSet());
        dto.setPlayerIds(playerIds);
    }

    Team toEntity(CreateTeamDto teamDto);

    @Mapping(target = "id", ignore = true)
    void updateFromDto(UpdateTeamDto dto, @MappingTarget Team team);
}
