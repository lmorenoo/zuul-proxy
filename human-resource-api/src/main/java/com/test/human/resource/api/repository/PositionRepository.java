package com.test.human.resource.api.repository;

import com.test.human.resource.api.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {

  boolean existsByName(String name);

  Optional<Position> findByName(String name);
}
