package com.microapp.footballmanager.dto.transfer;

import com.microapp.footballmanager.model.Transfer;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class TransferDto {
    private Long playerId;
    private Long fromTeamId;
    private Long toTeamId;
    private Long transferCost;
    private LocalDateTime transferDate;
    private String status;

    public TransferDto(Long playerId, Long fromTeamId, Long toTeamId,
                       Long totalCost, Transfer.TransferStatus status,
                       LocalDateTime transferDate) {
        this.playerId = playerId;
        this.fromTeamId = fromTeamId;
        this.toTeamId = toTeamId;
        this.transferCost = totalCost;
        this.transferDate = transferDate;
        this.status = status.toString();
    }
}
