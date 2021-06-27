package com.test.human.resource.api.service;

import com.test.human.resource.api.model.Candidate;
import com.test.human.resource.api.model.dto.CandidateDTO;

import java.util.List;

public interface CandidateService {

  void save(CandidateDTO employee);

  List<CandidateDTO> findAll();

  Candidate findById(Long id);

}
