package com.microapp.footballmanager.dtos.team;

import com.microapp.footballmanager.dtos.player.PlayerDto;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamWithPlayersDto {
    private Long id;
    private String name;
    private Long budget;
    private Short transferFee;
    private Set<PlayerDto> players;
}
