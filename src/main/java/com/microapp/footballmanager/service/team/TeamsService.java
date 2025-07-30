package com.microapp.footballmanager.service.team;

import com.microapp.footballmanager.dto.team.CreateTeamDto;
import com.microapp.footballmanager.dto.team.TeamDto;
import com.microapp.footballmanager.dto.team.TeamWithPlayersDto;
import com.microapp.footballmanager.dto.team.UpdateTeamDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamsService {
    Page<TeamDto> findAll(Pageable pageable);

    Page<TeamWithPlayersDto> findAllWithPlayerData(Pageable pageable);

    TeamDto findById(long id);

    TeamWithPlayersDto findByWithPlayerDataById(long id);

    TeamDto save(CreateTeamDto createTeamDto);

    void deleteById(long id);

    TeamDto update(Long id, UpdateTeamDto bookRequestDto);

    TeamDto updateBudget(Long id, Long budget);
}
