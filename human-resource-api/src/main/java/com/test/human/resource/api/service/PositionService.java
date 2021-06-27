package com.test.human.resource.api.service;

import com.test.human.resource.api.model.Position;
import com.test.human.resource.api.model.dto.PositionDTO;

import java.util.List;

public interface PositionService {

  PositionDTO save(PositionDTO employee);

  List<PositionDTO> findAll();

  Position findByName(String name);

}
