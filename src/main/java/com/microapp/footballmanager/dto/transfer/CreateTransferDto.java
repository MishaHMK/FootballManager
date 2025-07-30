package com.microapp.footballmanager.dto.transfer;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTransferDto {
    @Min(value = 1, message = "Player id must be bigger than 0")
    @NotNull(message = "Player ID is required")
    private Long playerId;

    @Min(value = 1, message = "Team id must be bigger than 0")
    @NotNull(message = "Team to transfer ID is required")
    private Long toTeamId;
}
