package com.test.human.resource.api.controller;

import com.test.human.resource.api.service.CandidateService;
import com.test.human.resource.api.model.dto.CandidateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<Void> createCandidate(@Valid @NotNull @RequestBody CandidateDTO CandidateDTO) {
        candidateService.save(CandidateDTO);
        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping
    public ResponseEntity<List<CandidateDTO>> getCandidates() {
        return ResponseEntity.ok(candidateService.findAll());
    }

}
