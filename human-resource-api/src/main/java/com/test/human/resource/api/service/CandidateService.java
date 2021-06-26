package com.test.human.resource.api.service;

import com.test.human.resource.api.model.Candidate;
import com.test.human.resource.api.model.dto.CandidateDTO;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

    void save(CandidateDTO employee);

    List<CandidateDTO> findAll();

    Optional<Candidate> findById(Long id);

}
