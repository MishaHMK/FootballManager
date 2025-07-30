package com.microapp.footballmanager.service.positions;

import com.microapp.footballmanager.dto.position.CreatePositionDto;
import com.microapp.footballmanager.dto.position.PositionDto;
import com.microapp.footballmanager.mapper.PositionsMapper;
import com.microapp.footballmanager.model.Position;
import com.microapp.footballmanager.repository.PositionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PositionsServiceImpl implements PositionsService {
    private final PositionsRepository positionsRepository;
    private final PositionsMapper positionsMapper;

    @Override
    public Page<PositionDto> findAll(Pageable pageable) {
        return positionsRepository.findAll(pageable)
                .map(positionsMapper::toDto);
    }

    @Transactional
    @Override
    public PositionDto save(CreatePositionDto createPositionDto) {
        if (checkPositionExistsByName(createPositionDto.getName())) {
            throw new IllegalArgumentException("Position already exists: "
                    + createPositionDto.getName());
        }

        Position newPosition = positionsMapper.toEntity(createPositionDto);
        return positionsMapper.toDto(positionsRepository.save(newPosition));
    }

    @Override
    public void deleteById(Long id) {
        if (checkPositionExistsById(id)) {
            positionsRepository.deleteById(id);
        }
    }

    private boolean checkPositionExistsByName(String positionName) {
        return positionsRepository.existsByName(positionName);
    }

    private boolean checkPositionExistsById(Long id) {
        return positionsRepository.existsById(id);
    }
}
