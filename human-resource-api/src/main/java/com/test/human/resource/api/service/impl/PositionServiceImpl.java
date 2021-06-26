package com.test.human.resource.api.service.impl;

import com.test.human.resource.api.exception.PositionAlreadyExistsException;
import com.test.human.resource.api.model.Position;
import com.test.human.resource.api.model.dto.PositionDTO;
import com.test.human.resource.api.repository.PositionRepository;
import com.test.human.resource.api.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public PositionDTO save(PositionDTO positionDTO) {
        if (positionRepository.existsByName(positionDTO.getName())) {
            throw new PositionAlreadyExistsException(positionDTO.getName());
        }
        var newPosition = positionRepository.save(new Position(positionDTO.getName()));
        return new PositionDTO(newPosition);
    }

    @Override
    public List<PositionDTO> findAll() {
        return positionRepository.findAll()
                .stream()
                .map(PositionDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Position> findByName(String name) {
        return positionRepository.findByName(name);
    }
}
