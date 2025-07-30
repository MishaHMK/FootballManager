package com.microapp.footballmanager.service.positions;

import com.microapp.footballmanager.dto.position.CreatePositionDto;
import com.microapp.footballmanager.dto.position.PositionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PositionsService {
    Page<PositionDto> findAll(Pageable pageable);

    PositionDto save(CreatePositionDto createPositionDto);

    void deleteById(Long id);
}
