package com.microapp.footballmanager.service.team;

import com.microapp.footballmanager.dtos.team.CreateTeamDto;
import com.microapp.footballmanager.dtos.team.TeamDto;
import com.microapp.footballmanager.dtos.team.TeamWithPlayersDto;
import com.microapp.footballmanager.dtos.team.UpdateTeamDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface TeamsService {
    List<TeamDto> findAll(Pageable pageable);

    List<TeamWithPlayersDto> findAllWithPlayerData(Pageable pageable);

    TeamDto findById(long id);

    TeamDto save(CreateTeamDto createTeamDto);

    void deleteById(long id);

    TeamDto update(Long id, UpdateTeamDto bookRequestDto);

    TeamDto updateBudget(Long id, Long budget);
}
