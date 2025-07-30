package com.microapp.footballmanager.dto.player;

import java.time.LocalDate;
import lombok.Data;

@Data
public class SimplePlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long teamId;
    private LocalDate birthDate;
    private LocalDate careerStartDate;
}
