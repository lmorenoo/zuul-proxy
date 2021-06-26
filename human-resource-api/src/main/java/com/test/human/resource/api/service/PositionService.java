package com.test.human.resource.api.service;

import com.test.human.resource.api.model.Position;
import com.test.human.resource.api.model.dto.PositionDTO;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    PositionDTO save(PositionDTO employee);

    List<PositionDTO> findAll();

    Optional<Position> findByName(String name);

}
