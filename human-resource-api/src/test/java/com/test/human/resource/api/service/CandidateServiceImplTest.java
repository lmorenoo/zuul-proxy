package com.test.human.resource.api.service;

import com.github.javafaker.Faker;
import com.test.human.resource.api.exception.CandidateNotFoundException;
import com.test.human.resource.api.model.Candidate;
import com.test.human.resource.api.model.dto.CandidateDTO;
import com.test.human.resource.api.model.dto.CandidateDTOMother;
import com.test.human.resource.api.repository.CandidateRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {

  @Autowired
  private CandidateService candidateService;

  @Autowired
  private CandidateRepository candidateRepository;

  @After
  public void after() {
    candidateRepository.deleteAll();
  }

  @Test
  public void shouldSaveACandidate() {
    var candidate = CandidateDTOMother.random();

    candidateService.save(candidate);

    var actualCandidate = candidateRepository.findById(candidate.getId());

    assertThat(actualCandidate).isNotNull().isPresent().get().extracting(Candidate::getId).isEqualTo(candidate.getId());
  }

  @Test
  public void shouldFindAllCandidates() {
    var candidate = CandidateDTOMother.random();

    candidateService.save(candidate);

    var candidates = candidateService.findAll();
    assertThat(candidates).isNotEmpty().size().isOne();
    assertThat(candidates).first().extracting(CandidateDTO::getId).isEqualTo(candidate.getId());
  }

  @Test
  public void shouldFindById() {
    var candidate = CandidateDTOMother.random();

    candidateService.save(candidate);

    var actualCandidate = candidateService.findById(candidate.getId());
    assertThat(actualCandidate).isNotNull().extracting(Candidate::getId).isEqualTo(candidate.getId());
  }

  @Test(expected = CandidateNotFoundException.class)
  public void shouldNotFindById() {
    candidateService.findById(new Faker().number().randomNumber());
  }

}
