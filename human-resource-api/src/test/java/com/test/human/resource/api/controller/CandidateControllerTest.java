package com.test.human.resource.api.controller;

import com.test.human.resource.api.model.dto.CandidateDTOMother;
import com.test.human.resource.api.model.dto.PositionDTOMother;
import com.test.human.resource.api.service.CandidateService;
import com.test.human.resource.api.service.PositionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

public class CandidateControllerTest {

  private CandidateController candidateController;

  @Mock
  private CandidateService candidateService;

  @Before
  public void before() {
    initMocks(this);
    candidateController = new CandidateController(candidateService);
  }

  @Test
  public void shouldReturn201() {
    var candidateDTO = CandidateDTOMother.random();

    var response = candidateController.createCandidate(candidateDTO);

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isNull();
  }

  @Test
  public void shouldReturn200WithAllPositions() {
    var candidates = List.of(CandidateDTOMother.random());

    given(candidateService.findAll()).willReturn(candidates);

    var response = candidateController.getCandidates();

    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isNotNull().isEqualTo(candidates);
  }

}
