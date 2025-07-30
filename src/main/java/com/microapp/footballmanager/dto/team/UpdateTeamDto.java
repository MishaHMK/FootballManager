package com.microapp.footballmanager.dto.team;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateTeamDto {
    @NotBlank(message = "Team name is required")
    @Size(min = 2, max = 25, message = "Team name must be at least 2 characters long "
            + "and not longer than 25 characters")
    private String name;

    @NotNull(message = "Team budget number in $ is required")
    private Long budget;

    @Min(value = 0, message = "Team transfer fee must be at least 0")
    @Max(value = 10, message = "Team transfer fee can't be bigger than 10")
    @NotNull(message = "Team transfer fee number is required")
    private Short transferFee;
}
