package com.microapp.footballmanager.service.team;

import com.microapp.footballmanager.dtos.team.CreateTeamDto;
import com.microapp.footballmanager.dtos.team.TeamDto;
import com.microapp.footballmanager.dtos.team.TeamWithPlayersDto;
import com.microapp.footballmanager.dtos.team.UpdateTeamDto;
import com.microapp.footballmanager.mapper.TeamMapper;
import com.microapp.footballmanager.model.Team;
import com.microapp.footballmanager.repository.TeamsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public List<TeamDto> findAll(Pageable pageable) {
        return teamsRepository.findAll(pageable)
                .stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @Override
    public List<TeamWithPlayersDto> findAllWithPlayerData(Pageable pageable) {
        return teamsRepository.findAll(pageable)
                .stream()
                .map(teamMapper::toDtoWithPlayers)
                .toList();
    }

    @Override
    public TeamDto findById(long id) {
        Team team = getTeamByIdWithPlayers(id);
        return teamMapper.toDto(team);
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
        getTeamById(id);
        teamsRepository.deleteById(id);
    }

    @Transactional
    @Override
    public TeamDto update(Long id, UpdateTeamDto teamRequestDto) {
        Team teamToUpdate = getTeamByIdWithPlayers(id);
        teamMapper.updateFromDto(teamRequestDto, teamToUpdate);
        teamsRepository.save(teamToUpdate);
        Team updatedTeam = getTeamByIdWithPlayers(id);
        return teamMapper.toDto(updatedTeam);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public TeamDto updateBudget(Long id, Long budget) {
        Team teamToUpdate = getTeamByIdWithPlayers(id);
        teamToUpdate.setBudget(budget);
        teamsRepository.save(teamToUpdate);
        Team updatedTeam = getTeamByIdWithPlayers(id);
        return teamMapper.toDto(updatedTeam);
    }

    private Team getTeamById(Long id) {
        return teamsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find team by id " + id));
    }

    private Team getTeamByIdWithPlayers(Long id) {
        return teamsRepository.getTeamByIdWithPlayers(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find team by id " + id));
    }
}
