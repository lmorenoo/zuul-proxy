package com.test.human.resource.api.controller;

import com.test.human.resource.api.service.EmployeeService;
import com.test.human.resource.api.model.dto.EmployeeDTO;
import com.test.human.resource.api.model.dto.EmployeeDetailDTO;
import com.test.human.resource.api.model.dto.EmployeePositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDetailDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        var newEmployee = employeeService.save(employeeDTO);
        return ResponseEntity.created(URI.create("")).body(newEmployee);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<EmployeeDetailDTO> updateEmployee(@NotNull @PathVariable(value = "id") Long id,
                                                            @Valid @RequestBody EmployeeDTO employeeDTO) {
        var employeeUpdated = employeeService.update(id, employeeDTO);
        return ResponseEntity.ok(employeeUpdated);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> getEmployeeById(@PathVariable(value = "id") Long id) {
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDetailDTO>> getEmployees(@RequestParam(value = "position", required = false) String position,
                                                                @RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.ok(employeeService.findAllByPositionAndName(position, name));
    }

    @GetMapping(value = "positions")
    public ResponseEntity<List<EmployeePositionDTO>> getEmployeesByPosition() {
        return ResponseEntity.ok(employeeService.findAll());
    }


}
