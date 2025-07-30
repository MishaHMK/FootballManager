package com.microapp.footballmanager.service.transfer;

import com.microapp.footballmanager.dto.player.PlayerDto;
import com.microapp.footballmanager.dto.team.TeamDto;
import com.microapp.footballmanager.dto.transfer.TransferDto;
import com.microapp.footballmanager.exception.TransferProcessingException;
import com.microapp.footballmanager.service.player.PlayersService;
import com.microapp.footballmanager.service.team.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {
    private static final int PRICE_RATE = 100000;

    private final TeamsService teamsService;
    private final PlayersService playersService;

    @Transactional
    @Override
    public TransferDto completeTransfer(Long playerId, Long toTeamId) {
        TeamDto transferTo = teamsService.findById(toTeamId);
        PlayerDto byPlayer = playersService.findById(playerId);
        TeamDto transferFrom = teamsService.findById(byPlayer.getTeamId());

        long rawCost = (byPlayer.getExperience() * PRICE_RATE) / byPlayer.getAge();
        long feeCost = (rawCost / 100) * transferFrom.getTransferFee();
        long totalCost = rawCost + feeCost;

        validatePlayerTransfer(transferTo, transferFrom, byPlayer, totalCost);

        teamsService.updateBudget(toTeamId, transferTo.getBudget() - totalCost);
        teamsService.updateBudget(byPlayer.getTeamId(), transferFrom.getBudget() + totalCost);
        playersService.updateTeam(playerId, toTeamId);

        return new TransferDto(playerId, byPlayer.getTeamId(), toTeamId, totalCost);
    }

    private void validatePlayerTransfer(TeamDto transferTo, TeamDto transferFrom,
                                        PlayerDto player, Long totalCost) {
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
