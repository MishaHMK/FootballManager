package com.microapp.footballmanager.dto.team;

import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TeamDto {
    private Long id;
    private String name;
    private Long budget;
    private Short transferFee;
    private Set<Long> playerIds;
}
