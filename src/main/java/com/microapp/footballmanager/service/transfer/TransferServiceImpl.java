package com.microapp.footballmanager.service.transfer;

import com.microapp.footballmanager.dto.player.PlayerDto;
import com.microapp.footballmanager.dto.team.TeamDto;
import com.microapp.footballmanager.dto.transfer.TransferDto;
import com.microapp.footballmanager.exception.TransferProcessingException;
import com.microapp.footballmanager.mapper.TransferMapper;
import com.microapp.footballmanager.model.Player;
import com.microapp.footballmanager.model.Team;
import com.microapp.footballmanager.model.Transfer;
import com.microapp.footballmanager.repository.TransferRepository;
import com.microapp.footballmanager.service.player.PlayersService;
import com.microapp.footballmanager.service.team.TeamsService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private static final int PRICE_RATE = 100000;

    private final TeamsService teamsService;
    private final PlayersService playersService;
    private final TransferRepository transferRepository;
    private final TransferMapper transferMapper;

    @Transactional
    @Override
    public TransferDto completeTransfer(Long playerId, Long toTeamId) {
        TeamDto transferTo = teamsService.findById(toTeamId);
        PlayerDto byPlayer = playersService.findById(playerId);
        TeamDto transferFrom = teamsService.findById(byPlayer.getTeamId());

        long rawCost = (byPlayer.getExperience() * PRICE_RATE) / byPlayer.getAge();
        long feeCost = (rawCost / 100) * transferFrom.getTransferFee();
        long totalCost = rawCost + feeCost;

        validatePlayerTransfer(transferTo, byPlayer, totalCost);

        teamsService.updateBudget(toTeamId, transferTo.getBudget() - totalCost);
        teamsService.updateBudget(byPlayer.getTeamId(), transferFrom.getBudget() + totalCost);
        playersService.updateTeam(playerId, toTeamId);

        Transfer transfer = createTransfer(playerId, toTeamId,
                transferFrom.getId(), totalCost);

        return transferMapper.toDto(transferRepository.save(transfer));
    }

    @Override
    public Page<TransferDto> findAll(Pageable pageable) {
        return transferRepository.findAll(pageable)
                .map(transferMapper::toDto);
    }

    @Override
    public TransferDto findById(Long id) {
        Transfer transferWithData = getTransferById(id);
        return transferMapper.toDto(transferWithData);
    }

    @Override
    public TransferDto cancelTransfer(Long id) {
        Transfer transferWithData = getTransferById(id);
        Long feeAmount = (transferWithData.getTransferCost() / 100)
                * transferWithData.getFromTeam().getTransferFee();
        Long refundCost = transferWithData.getTransferCost()
                - feeAmount;
        Team fromTeam = transferWithData.getFromTeam();
        Team toTeam = transferWithData.getToTeam();

        teamsService.updateBudget(fromTeam.getId(),
                fromTeam.getBudget() - refundCost);
        teamsService.updateBudget(toTeam.getId(),
                toTeam.getBudget() + refundCost);

        transferWithData.setStatus(Transfer.TransferStatus.CANCELLED);

        return transferMapper.toDto(transferRepository.save(transferWithData));
    }

    private Transfer getTransferById(Long id) {
        return transferRepository.getTransferWithData(id);
    }

    private Transfer createTransfer(Long playerId, Long toTeamId,
                                    Long fromTeamId, Long transferCost) {
        return new Transfer()
                .setPlayer(new Player().setId(playerId))
                .setFromTeam(new Team().setId(fromTeamId))
                .setToTeam(new Team().setId(toTeamId))
                .setTransferCost(transferCost)
                .setTransferDate(LocalDateTime.now())
                .setStatus(Transfer.TransferStatus.COMPLETED);
    }

    private void validatePlayerTransfer(TeamDto transferTo, PlayerDto player, Long totalCost) {
        if (player.getTeamId() == null) {
            throw new TransferProcessingException("Player is not currently assigned to any team");
        }
        if (player.getTeamId().equals(transferTo.getId())) {
            throw new TransferProcessingException("Player is already in the destination team");
        }
        if (transferTo.getBudget() - totalCost < 0) {
            throw new TransferProcessingException("Budget of team " + transferTo.getName()
                    + " is insufficient \n "
                    + " Transfer cost: " + totalCost
                    + " Team budget: " + transferTo.getBudget());
        }
    }
}
