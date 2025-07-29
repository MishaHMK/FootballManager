package com.microapp.footballmanager.controller;

import com.microapp.footballmanager.dtos.team.CreateTeamDto;
import com.microapp.footballmanager.dtos.team.TeamDto;
import com.microapp.footballmanager.dtos.team.TeamWithPlayersDto;
import com.microapp.footballmanager.dtos.team.UpdateTeamDto;
import com.microapp.footballmanager.service.team.TeamsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Team Management", description = "Endpoints for managing football teams")
@RequiredArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamsController {
    private final TeamsService teamsService;

    @GetMapping
    @Operation(summary = "Get all teams",
            description = "Get all teams with pagination/sorting")
    public List<TeamDto> getAll(@ParameterObject Pageable pageable) {
        return teamsService.findAll(pageable);
    }

    @GetMapping("/full-data")
    @Operation(summary = "Get all teams with players data",
            description = "Get all teams with full players data and pagination/sorting")
    public List<TeamWithPlayersDto> getAllWithPlayersData(@ParameterObject Pageable pageable) {
        return teamsService.findAllWithPlayerData(pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get team by id",
            description = "Get specific team data by its id")
    public TeamDto getById(@PathVariable Long id) {
        return teamsService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create team",
            description = "Create new team with given data")
    public TeamDto createNew(@Valid @RequestBody CreateTeamDto teamDto) {
        return teamsService.save(teamDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete team by id",
            description = "Mark specific team by given id as removed")
    public void deleteById(@PathVariable Long id) {
        teamsService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update team by id",
            description = "Update specific team with given data by its id")
    public TeamDto updateById(@PathVariable Long id, @Valid @RequestBody UpdateTeamDto updateDto) {
        return teamsService.update(id, updateDto);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Change team budget by id",
            description = "Change specific team budget by its id")
    public TeamDto changeBudget(@PathVariable Long id, @RequestParam Long newBudget) {
        return teamsService.updateBudget(id, newBudget);
    }
}
