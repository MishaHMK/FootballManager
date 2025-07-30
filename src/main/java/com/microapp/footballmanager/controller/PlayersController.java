package com.microapp.footballmanager.controller;

import com.microapp.footballmanager.dto.player.CreatePlayerDto;
import com.microapp.footballmanager.dto.player.PlayerDto;
import com.microapp.footballmanager.dto.player.SimplePlayerDto;
import com.microapp.footballmanager.dto.player.UpdatePlayerDto;
import com.microapp.footballmanager.service.player.PlayersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Player Management", description = "Endpoints for managing football players")
@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class PlayersController {
    private final PlayersService playersService;

    @GetMapping
    @Operation(summary = "Get all players",
            description = "Get all players with pagination/sorting")
    public Page<PlayerDto> getAll(@ParameterObject Pageable pageable) {
        return playersService.findAll(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get player by id",
            description = "Get specific player data by its id")
    public PlayerDto getById(@PathVariable Long id) {
        return playersService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create player",
            description = "Create new player with given data")
    public SimplePlayerDto createNew(@Valid @RequestBody CreatePlayerDto playerDto) {
        return playersService.save(playerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete player by id",
            description = "Mark specific player by given id as removed")
    public void deleteById(@PathVariable Long id) {
        playersService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update player by id",
            description = "Update specific player with given data by its id")
    public PlayerDto updateById(@PathVariable Long id,
                                @Valid @RequestBody UpdatePlayerDto updateDto) {
        return playersService.update(id, updateDto);
    }
}
