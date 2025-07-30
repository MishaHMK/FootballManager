package com.microapp.footballmanager.repository;

import com.microapp.footballmanager.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository<Position, Long> {
    boolean existsByName(String positionName);
}
