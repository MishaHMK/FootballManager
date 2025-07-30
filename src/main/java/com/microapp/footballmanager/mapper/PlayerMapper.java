package com.microapp.footballmanager.mapper;

import com.microapp.footballmanager.config.MapperConfig;
import com.microapp.footballmanager.dto.player.CreatePlayerDto;
import com.microapp.footballmanager.dto.player.PlayerDto;
import com.microapp.footballmanager.dto.player.SimplePlayerDto;
import com.microapp.footballmanager.dto.player.UpdatePlayerDto;
import com.microapp.footballmanager.model.Player;
import com.microapp.footballmanager.model.Position;
import com.microapp.footballmanager.model.Team;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", config = MapperConfig.class)
public interface PlayerMapper {
    @Mapping(target = "positions", source = "positions", qualifiedByName = "positionToString")
    PlayerDto toDto(Player player);

    @Mapping(target = "teamId", source = "team.id")
    SimplePlayerDto toSimpleDto(Player player);

    @Mapping(target = "team", source = "teamId", qualifiedByName = "teamById")
    @Mapping(target = "positions", source = "positionIds", qualifiedByName = "positionsByIds")
    Player toEntity(CreatePlayerDto playerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "positions", source = "positionIds", qualifiedByName = "positionsByIds")
    void updateFromDto(UpdatePlayerDto dto, @MappingTarget Player player);

    @AfterMapping
    default void setDtoData(@MappingTarget PlayerDto dto, Player player) {
        Team team = player.getTeam();
        dto.setTeamName(team.getName());
        int age = Period.between(player.getBirthDate(), LocalDate.now()).getYears();
        dto.setAge(age);
        long experience = ChronoUnit.MONTHS.between(player.getCareerStartDate(), LocalDate.now());
        dto.setExperience(experience);
        dto.setTeamId(team.getId());
    }

    @Named("teamById")
    default Team mapTeamIdToTeam(Long id) {
        Team team = new Team();
        team.setId(id);
        return team;
    }

    @Named("positionToString")
    default Set<String> mapTeamIdToTeam(Set<Position> positions) {
        return positions.stream()
                .map(Position::getName)
                .collect(Collectors.toSet());
    }

    @Named("positionsByIds")
    default Set<Position> positionsFromIds(Set<Long> positionIds) {
        return positionIds.stream()
                .map(id -> {
                    Position position = new Position();
                    position.setId(id);
                    return position;
                })
                .collect(Collectors.toSet());
    }
}
