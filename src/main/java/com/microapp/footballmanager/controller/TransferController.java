package com.microapp.footballmanager.controller;

import com.microapp.footballmanager.dtos.transfer.CreateTransferDto;
import com.microapp.footballmanager.dtos.transfer.TransferDto;
import com.microapp.footballmanager.service.transfer.TransferService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create transfer",
            description = "Create new transfer with given data")
    public TransferDto makeTransfer(@Valid @RequestBody CreateTransferDto createTransferDto) {
        return transferService.completeTransfer(createTransferDto.getPlayerId(),
                createTransferDto.getToTeamId());
    }
}
