package com.test.human.resource.api.repository;

import com.test.human.resource.api.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

}
