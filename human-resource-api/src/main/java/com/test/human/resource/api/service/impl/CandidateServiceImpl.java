package com.test.human.resource.api.service.impl;

import com.test.human.resource.api.exception.CandidateAlreadyExistsException;
import com.test.human.resource.api.exception.CandidateNotFoundException;
import com.test.human.resource.api.model.Candidate;
import com.test.human.resource.api.model.dto.CandidateDTO;
import com.test.human.resource.api.repository.CandidateRepository;
import com.test.human.resource.api.service.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidateServiceImpl implements CandidateService {

  private final CandidateRepository candidateRepository;

  private final ModelMapper modelMapper;

  public CandidateServiceImpl(CandidateRepository candidateRepository, ModelMapper modelMapper) {
    this.candidateRepository = candidateRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public void save(CandidateDTO candidateDTO) {
    if (candidateRepository.existsById(candidateDTO.getId())) {
      throw new CandidateAlreadyExistsException(candidateDTO.getId());
    }
    var candidate = modelMapper.map(candidateDTO, Candidate.class);
    candidateRepository.save(candidate);
  }

  @Override
  public List<CandidateDTO> findAll() {
    return candidateRepository.findAll()
      .stream()
      .map(CandidateDTO::new)
      .collect(Collectors.toList());
  }

  @Override
  public Candidate findById(Long id) {
    return candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
  }
}
