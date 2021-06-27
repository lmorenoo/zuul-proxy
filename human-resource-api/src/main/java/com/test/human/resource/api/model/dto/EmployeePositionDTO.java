package com.test.human.resource.api.model.dto;

import com.test.human.resource.api.model.Employee;
import com.test.human.resource.api.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeePositionDTO {

  private Long id;

  private String name;

  private List<EmployeeDetailDTO> employees;

  public EmployeePositionDTO(Position position, List<Employee> employees) {
    this.id = position.getId();
    this.name = position.getName();
    this.employees = employees.stream().map(EmployeeDetailDTO::new).collect(Collectors.toList());
  }
}
