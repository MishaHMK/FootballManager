package com.microapp.footballmanager.controller;

import com.microapp.footballmanager.dto.transfer.CreateTransferDto;
import com.microapp.footballmanager.dto.transfer.TransferDto;
import com.microapp.footballmanager.service.transfer.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Transfer Management", description = "Endpoints for managing football player transfers")
@RequiredArgsConstructor
@RestController
@RequestMapping("/transfers")
public class TransferController {
    private final TransferService transferService;

    @GetMapping("/{id}")
    @Operation(summary = "Get transfer by id",
            description = "Get specific transfer data by its id")
    public TransferDto getById(@PathVariable Long id) {
        return transferService.findById(id);
    }

    @GetMapping
    @Operation(summary = "Get all transfers",
            description = "Get all transfers with pagination/sorting")
    public Page<TransferDto> getAll(@ParameterObject Pageable pageable) {
        return transferService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create transfer",
            description = "Create new transfer with given data")
    public TransferDto makeTransfer(@Valid @RequestBody CreateTransferDto createTransferDto) {
        return transferService.completeTransfer(createTransferDto.getPlayerId(),
                createTransferDto.getToTeamId());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Cancel transfer",
            description = "Transfer fee remains with team, which returns transfer money")
    public TransferDto cancelTransfer(@PathVariable Long id) {
        return transferService.cancelTransfer(id);
    }
}
