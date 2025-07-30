package com.microapp.footballmanager.service.transfer;

import com.microapp.footballmanager.dto.transfer.TransferDto;

public interface TransferService {
    TransferDto completeTransfer(Long playerId, Long toTeamId);
}
