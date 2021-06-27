package com.test.human.resource.api.controller;

import com.test.human.resource.api.model.dto.PositionDTOMother;
import com.test.human.resource.api.service.PositionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class PositionControllerTest {

  private PositionController positionController;

  @Mock
  private PositionService positionService;

  @Before
  public void before() {
    initMocks(this);
    positionController = new PositionController(positionService);
  }

  @Test
  public void shouldReturn201WithAPosition() {
    var positionDTO = PositionDTOMother.random();

    given(positionService.save(positionDTO)).willReturn(positionDTO);

    var response = positionController.createPosition(positionDTO);

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(positionDTO);
  }

  @Test
  public void shouldReturn200WithAllPositions() {
    var positions = List.of(PositionDTOMother.random());

    given(positionService.findAll()).willReturn(positions);

    var response = positionController.getEmployees();

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull().isEqualTo(positions);
  }

}
