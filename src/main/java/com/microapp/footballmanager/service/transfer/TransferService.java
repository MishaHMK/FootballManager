package com.microapp.footballmanager.service.transfer;

import com.microapp.footballmanager.dto.transfer.TransferDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferService {
    TransferDto completeTransfer(Long playerId, Long toTeamId);

    Page<TransferDto> findAll(Pageable pageable);

    TransferDto findById(Long id);

    TransferDto cancelTransfer(Long id);
}
