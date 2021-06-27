package com.test.human.resource.api.service;

import com.github.javafaker.Faker;
import com.test.human.resource.api.exception.PositionNotFoundException;
import com.test.human.resource.api.model.Position;
import com.test.human.resource.api.model.dto.PositionDTO;
import com.test.human.resource.api.model.dto.PositionDTOMother;
import com.test.human.resource.api.repository.PositionRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PositionServiceImplTest {

  @Autowired
  private PositionService positionService;

  @Autowired
  private PositionRepository positionRepository;

  @After
  public void after() {
    positionRepository.deleteAll();
  }

  @Test
  public void shouldSaveAPosition() {
    var position = PositionDTOMother.random();

    positionService.save(position);

    var actualPosition = positionRepository.findByName(position.getName());

    assertThat(actualPosition).isNotNull().isPresent().get().extracting(Position::getName).isEqualTo(position.getName());
  }

  @Test
  public void shouldFindAllPositions() {
    var Position = PositionDTOMother.random();

    positionService.save(Position);

    var Positions = positionService.findAll();
    assertThat(Positions).isNotEmpty().size().isOne();
    assertThat(Positions).first().extracting(PositionDTO::getName).isEqualTo(Position.getName());
  }

  @Test
  public void shouldFindByName() {
    var position = PositionDTOMother.random();

    positionService.save(position);

    var actualPosition = positionService.findByName(position.getName());
    assertThat(actualPosition).isNotNull().extracting(Position::getName).isEqualTo(position.getName());
  }

  @Test(expected = PositionNotFoundException.class)
  public void shouldNotFindById() {
    positionService.findByName(new Faker().company().profession());
  }

}
