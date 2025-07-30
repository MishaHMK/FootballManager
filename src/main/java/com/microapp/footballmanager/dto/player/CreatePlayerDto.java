package com.microapp.footballmanager.dto.player;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CreatePlayerDto {
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 25, message = "Player's first name must be at least "
            + "2 characters long and not longer than 25 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 25, message = "Player's last name must be at least "
            + "2 characters long and not longer than 25 characters")
    private String lastName;

    @Min(value = 1, message = "Team id must be bigger than 0")
    @NotNull(message = "Team id is required")
    private Long teamId;

    @NotNull(message = "Player's birth date is required")
    private LocalDate birthDate;

    @NotNull(message = "Player's career start date is required")
    private LocalDate careerStartDate;

    @NotEmpty(message = "Player's positions ids are required")
    private Set<Long> positionIds = new HashSet<>();
}
