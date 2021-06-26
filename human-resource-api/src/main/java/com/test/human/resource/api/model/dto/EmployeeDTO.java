package com.test.human.resource.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.human.resource.api.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class EmployeeDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull
    @Min(value = 1, message = "The min value must be greater than 0")
    private Long personId;

    @NotBlank
    private String positionName;

    @NotNull
    @Min(value = 0)
    private Double salary;

    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.personId = employee.getPerson().getId();
        this.positionName = employee.getPosition().getName();
        this.salary = employee.getSalary();
    }
}
