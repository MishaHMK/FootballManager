package com.microapp.footballmanager.dto.transfer;

import lombok.Data;

@Data
public class TransferDto {
    private Long playerId;
    private Long fromTeamId;
    private Long toTeamId;
    private Long cost;

    public TransferDto(Long playerId, Long fromTeamId, Long toTeamId, long totalCost) {
        this.playerId = playerId;
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
        this.cost = totalCost;
    }
}
