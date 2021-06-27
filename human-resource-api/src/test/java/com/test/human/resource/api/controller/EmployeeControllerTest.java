package com.test.human.resource.api.controller;

import com.test.human.resource.api.model.dto.*;
import com.test.human.resource.api.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

public class EmployeeControllerTest {

  private EmployeeController employeeController;

  @Mock
  private EmployeeService employeeService;

  @Before
  public void before() {
    initMocks(this);
    employeeController = new EmployeeController(employeeService);
  }

  @Test
  public void shouldReturn201WithANewEmployee() {
    var employeeDTO = EmployeeDTOMother.random();
    var candidateDTO = CandidateDTOMother.random();
    var employeeDetailDTO = new EmployeeDetailDTO(employeeDTO.getId(), employeeDTO.getSalary(), candidateDTO);
    given(employeeService.save(employeeDTO)).willReturn(employeeDetailDTO);

    var response = employeeController.createEmployee(employeeDTO);

    then(employeeService).should().save(employeeDTO);
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    assertThat(response.getBody()).isEqualTo(employeeDetailDTO);
  }

  @Test
  public void shouldUpdateEmployee() {
    var employeeDTO = EmployeeDTOMother.random();
    var candidateDTO = CandidateDTOMother.random();
    var employeeDetailDTO = new EmployeeDetailDTO(employeeDTO.getId(), employeeDTO.getSalary(), candidateDTO);
    given(employeeService.update(employeeDTO.getId(), employeeDTO)).willReturn(employeeDetailDTO);

    var response = employeeController.updateEmployee(employeeDTO.getId(), employeeDTO);

    then(employeeService).should().update(employeeDTO.getId(), employeeDTO);
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(employeeDetailDTO);
  }

  @Test
  public void shouldDeleteEmployeeById() {
    var employeeDTO = EmployeeDTOMother.random();

    var response = employeeController.deleteEmployeeById(employeeDTO.getId());

    then(employeeService).should().deleteById(employeeDTO.getId());
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    assertThat(response.getBody()).isNull();
  }

  @Test
  public void shouldGetEmployeesByPositionAndName() {
    var employeeDTO = EmployeeDTOMother.random();
    var candidateDTO = CandidateDTOMother.random();
    var positionDTO = PositionDTOMother.random();
    var employeeDetailDTO = new EmployeeDetailDTO(employeeDTO.getId(), employeeDTO.getSalary(), candidateDTO);
    var employees = List.of(employeeDetailDTO);
    given(employeeService.findAllByPositionAndName(positionDTO.getName(), candidateDTO.getName())).willReturn(employees);

    var response = employeeController.getEmployees(positionDTO.getName(), candidateDTO.getName());

    then(employeeService).should().findAllByPositionAndName(positionDTO.getName(), candidateDTO.getName());
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(employees);
  }

  @Test
  public void shouldGetEmployeesPosition() {
    var employeeDTO = EmployeeDTOMother.random();
    var candidateDTO = CandidateDTOMother.random();
    var positionDTO = PositionDTOMother.random();
    var employees = List.of(new EmployeeDetailDTO(employeeDTO.getId(), employeeDTO.getSalary(), candidateDTO));
    var employeesPosition = List.of(new EmployeePositionDTO(positionDTO.getId(), positionDTO.getName(), employees));
    given(employeeService.findAll()).willReturn(employeesPosition);

    var response = employeeController.getEmployeesPosition();

    then(employeeService).should().findAll();
    assertThat(response).isNotNull();
    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(response.getBody()).isEqualTo(employeesPosition);
  }

}
