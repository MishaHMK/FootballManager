package com.microapp.footballmanager.dto.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePositionDto {
    @NotBlank(message = "Position name is required")
    @Size(min = 2, max = 15, message = "Position name must be at least 2 characters long "
            + "and not longer than 15 characters")
    private String name;
}
