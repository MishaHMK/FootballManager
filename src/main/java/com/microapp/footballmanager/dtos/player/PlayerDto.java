package com.microapp.footballmanager.dtos.player;

import java.time.LocalDate;
import java.util.Set;
import lombok.Data;

@Data
public class PlayerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long teamId;
    private String teamName;
    private LocalDate birthDate;
    private Integer age;
    private LocalDate careerStartDate;
    private Long experience;
    private Set<String> positions;
}

