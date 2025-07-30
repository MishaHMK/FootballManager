package com.microapp.footballmanager.service.team;

import com.microapp.footballmanager.dto.team.CreateTeamDto;
import com.microapp.footballmanager.dto.team.TeamDto;
import com.microapp.footballmanager.dto.team.TeamWithPlayersDto;
import com.microapp.footballmanager.dto.team.UpdateTeamDto;
import com.microapp.footballmanager.mapper.TeamMapper;
import com.microapp.footballmanager.model.Team;
import com.microapp.footballmanager.repository.TeamsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamsServiceImpl implements TeamsService {
    private final TeamsRepository teamsRepository;
    private final TeamMapper teamMapper;

    @Override
    public Page<TeamDto> findAll(Pageable pageable) {
        return teamsRepository.findAll(pageable)
                .map(teamMapper::toDto);
    }

    @Override
    public Page<TeamWithPlayersDto> findAllWithPlayerData(Pageable pageable) {
        return teamsRepository.findAll(pageable)
                .map(teamMapper::toDtoWithPlayers);
    }

    @Override
    public TeamDto findById(long id) {
        return teamMapper.toDto(getTeamByIdWithPlayers(id));
    }

    @Override
    public TeamWithPlayersDto findByWithPlayerDataById(long id) {
        return teamMapper.toDtoWithPlayers(getTeamByIdWithPlayers(id));
    }

    @Transactional
    @Override
    public TeamDto save(CreateTeamDto createTeamDto) {
        Team newTeam = teamMapper.toEntity(createTeamDto);
        return teamMapper.toDto(teamsRepository.save(newTeam));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        if (checkTeamExistsById(id)) {
            teamsRepository.deleteById(id);
        }
    }

    @Transactional
    @Override
    public TeamDto update(Long id, UpdateTeamDto teamRequestDto) {
        Team teamToUpdate = getTeamByIdWithPlayers(id);
        teamMapper.updateFromDto(teamRequestDto, teamToUpdate);
        return teamMapper.toDto(teamToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public TeamDto updateBudget(Long id, Long budget) {
        Team teamToUpdate = getTeamByIdWithPlayers(id);
        teamToUpdate.setBudget(budget);
        return teamMapper.toDto(teamToUpdate);
    }

    private Team getTeamById(Long id) {
        return teamsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find team by id " + id));
    }

    private Team getTeamByIdWithPlayers(Long id) {
        return teamsRepository.getTeamByIdWithPlayers(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find team by id " + id));
    }

    private boolean checkTeamExistsById(Long id) {
        return teamsRepository.existsById(id);
    }
}
