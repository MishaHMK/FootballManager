package com.microapp.footballmanager.service.player;

import com.microapp.footballmanager.dto.player.CreatePlayerDto;
import com.microapp.footballmanager.dto.player.PlayerDto;
import com.microapp.footballmanager.dto.player.SimplePlayerDto;
import com.microapp.footballmanager.dto.player.UpdatePlayerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PlayersService {
    Page<PlayerDto> findAll(Pageable pageable);

    PlayerDto findById(long id);

    SimplePlayerDto save(CreatePlayerDto createPlayerDto);

    void deleteById(long id);

    PlayerDto update(Long id, UpdatePlayerDto updatePlayerDto);

    PlayerDto updateTeam(Long id, Long newTeamId);
}
