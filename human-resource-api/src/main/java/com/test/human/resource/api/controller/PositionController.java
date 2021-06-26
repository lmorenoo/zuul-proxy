package com.test.human.resource.api.controller;

import com.test.human.resource.api.service.PositionService;
import com.test.human.resource.api.model.dto.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/positions")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public ResponseEntity<PositionDTO> createPosition(@Valid @RequestBody PositionDTO positionDTO) {
        var newPosition = positionService.save(positionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPosition);
    }

    @GetMapping
    public ResponseEntity<List<PositionDTO>> getEmployees() {
        return ResponseEntity.ok(positionService.findAll());
    }

}
