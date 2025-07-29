package com.microapp.footballmanager.service.player;

import com.microapp.footballmanager.dtos.player.CreatePlayerDto;
import com.microapp.footballmanager.dtos.player.PlayerDto;
import com.microapp.footballmanager.dtos.player.UpdatePlayerDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface PlayersService {
    List<PlayerDto> findAll(Pageable pageable);

    PlayerDto findById(long id);

    PlayerDto save(CreatePlayerDto createPlayerDto);

    void deleteById(long id);

    PlayerDto update(Long id, UpdatePlayerDto updatePlayerDto);

    PlayerDto updateTeam(Long id, Long newTeamId);
}
