package com.microapp.footballmanager.service.player;

import com.microapp.footballmanager.dtos.player.CreatePlayerDto;
import com.microapp.footballmanager.dtos.player.PlayerDto;
import com.microapp.footballmanager.dtos.player.UpdatePlayerDto;
import com.microapp.footballmanager.mapper.PlayerMapper;
import com.microapp.footballmanager.model.Player;
import com.microapp.footballmanager.model.Position;
import com.microapp.footballmanager.model.Team;
import com.microapp.footballmanager.repository.PlayersRepository;
import com.microapp.footballmanager.repository.PositionsRepository;
import com.microapp.footballmanager.repository.TeamsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayersService {
    private final PlayersRepository playersRepository;
    private final PositionsRepository positionsRepository;
    private final TeamsRepository teamsRepository;
    private final PlayerMapper playerMapper;

    @Override
    public List<PlayerDto> findAll(Pageable pageable) {
        return playersRepository.findAll(pageable)
                .stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @Override
    public PlayerDto findById(long id) {
        Player playerByIdWithTeam = getPlayerByIdWithTeam(id);
        return playerMapper.toDto(playerByIdWithTeam);
    }

    @Transactional
    @Override
    public PlayerDto save(CreatePlayerDto createPlayerDto) {
        Player newPlayer = playerMapper.toEntity(createPlayerDto);
        return playerMapper.toDto(playersRepository.save(newPlayer));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        getPlayerById(id);
        playersRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PlayerDto update(Long id, UpdatePlayerDto updatePlayerDto) {
        Player playerToUpdate = getPlayerByIdWithTeam(id);
        playerMapper.updateFromDto(updatePlayerDto, playerToUpdate);
        return playerMapper.toDto(playerToUpdate);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PlayerDto updateTeam(Long id, Long newTeamId) {
        Player playerToUpdate = getPlayerByIdWithTeam(id);
        Team newTeam = teamsRepository.findById(newTeamId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find team by id " + id));
        playerToUpdate.setTeam(newTeam);
        return playerMapper.toDto(playerToUpdate);
    }

    private Player getPlayerByIdWithTeam(Long id) {
        return playersRepository.getPlayerByIdWithTeam(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find player by id " + id));
    }

    private Player getPlayerById(Long id) {
        return playersRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't find player by id " + id));
    }
}
