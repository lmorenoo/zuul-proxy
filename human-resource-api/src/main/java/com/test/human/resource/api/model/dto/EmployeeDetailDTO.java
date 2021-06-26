package com.test.human.resource.api.model.dto;

import com.test.human.resource.api.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDetailDTO {

    private Long id;

    private Double salary;

    private CandidateDTO person;

    public EmployeeDetailDTO(Employee employee) {
        this.id = employee.getId();
        this.salary = employee.getSalary();
        this.person = new CandidateDTO(employee.getPerson());
    }
}
