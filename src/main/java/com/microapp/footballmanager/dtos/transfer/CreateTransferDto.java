package com.microapp.footballmanager.dtos.transfer;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTransferDto {
    @NotNull(message = "Player ID is required")
    private Long playerId;

    @NotNull(message = "Team to transfer ID is required")
    private Long toTeamId;
}
