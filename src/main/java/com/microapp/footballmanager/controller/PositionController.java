package com.microapp.footballmanager.controller;

import com.microapp.footballmanager.dto.position.CreatePositionDto;
import com.microapp.footballmanager.dto.position.PositionDto;
import com.microapp.footballmanager.service.positions.PositionsService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Positions Management", description = "Endpoints for managing"
        + " positions for football players")
@RequiredArgsConstructor
@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionsService positionsService;

    @GetMapping
    @Operation(summary = "Get all positions",
            description = "Get all positions with pagination/sorting")
    public Page<PositionDto> getAll(@ParameterObject Pageable pageable) {
        return positionsService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create position",
            description = "Create new position with given name")
    public PositionDto createNew(@Valid @RequestBody CreatePositionDto createPositionDto) {
        return positionsService.save(createPositionDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Remove position by id",
            description = "Remove position from database by id")
    public void deleteById(@PathVariable Long id) {
        positionsService.deleteById(id);
    }
}


