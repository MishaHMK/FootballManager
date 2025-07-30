package com.microapp.footballmanager.dto.team;

import com.microapp.footballmanager.dto.player.PlayerDto;
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
