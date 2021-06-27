package com.test.human.resource.api.service;

import com.github.javafaker.Faker;
import com.test.human.resource.api.exception.CandidateNotFoundException;
import com.test.human.resource.api.exception.EmployeeAlreadyExistsException;
import com.test.human.resource.api.exception.EmployeeNotFoundException;
import com.test.human.resource.api.exception.PositionNotFoundException;
import com.test.human.resource.api.model.*;
import com.test.human.resource.api.model.dto.EmployeeDTOMother;
import com.test.human.resource.api.model.dto.EmployeePositionDTO;
import com.test.human.resource.api.repository.CandidateRepository;
import com.test.human.resource.api.repository.EmployeeRepository;
import com.test.human.resource.api.repository.PositionRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceImplTest {

  @Autowired
  private EmployeeService employeeService;

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
  private PositionRepository positionRepository;

  @Autowired
  private CandidateRepository candidateRepository;

  private Position position;

  private Candidate candidate;

  @Before
  public void before() {
    position = PositionMother.random();
    candidate = CandidateMother.random();

    positionRepository.save(position);
    candidateRepository.save(candidate);
  }

  @After
  public void after() {
    employeeRepository.deleteAll();
    positionRepository.deleteAll();
    candidateRepository.deleteAll();
  }

  @Test
  public void shouldSaveAEmployee() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    employeeService.save(employee);

    var employees = employeeRepository.findAll();

    assertThat(employees).isNotNull().size().isOne();
    assertThat(employees).first().extracting(actualEmployee -> actualEmployee.getPerson().getName()).isEqualTo(candidate.getName());
  }

  @Test(expected = EmployeeAlreadyExistsException.class)
  public void shouldFailSaveAEmployeeExistent() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    employeeService.save(employee);

    employeeService.save(employee);
  }

  @Test(expected = PositionNotFoundException.class)
  public void shouldFailSaveAEmployeeWithWringPosition() {
    var employee = EmployeeDTOMother.random(candidate.getId());

    employeeService.save(employee);
  }

  @Test(expected = CandidateNotFoundException.class)
  public void shouldFailSaveAEmployeeWithWringCandidate() {
    var employee = EmployeeDTOMother.random(position.getName());

    employeeService.save(employee);
  }

  @Test
  public void shouldUpdateAEmployee() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    var employeeSaved = employeeService.save(employee);

    employee.setSalary(new Faker().number().randomDouble(2, 0, 1000));
    employeeService.update(employeeSaved.getId(), employee);

    var employees = employeeRepository.findAll();

    assertThat(employees).isNotNull().size().isOne();
    assertThat(employees).first().extracting(actualEmployee -> actualEmployee.getPerson().getName(), Employee::getSalary)
      .contains(candidate.getName(), employee.getSalary());
  }

  @Test(expected = EmployeeNotFoundException.class)
  public void shouldFailUpdateANonExistentEmployee() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    employeeService.update(employee.getId(), employee);
  }

  @Test(expected = PositionNotFoundException.class)
  public void shouldFailUpdateAEmployeeWithWringPosition() {
    var employee = EmployeeDTOMother.random(candidate.getId());
    var employeeSaved = employeeService.save(employee);

    employeeService.update(employeeSaved.getId(), employee);
  }

  @Test(expected = CandidateNotFoundException.class)
  public void shouldFailUpdateAEmployeeWithWringCandidate() {
    var employee = EmployeeDTOMother.random(position.getName());
    var employeeSaved = employeeService.save(employee);

    employeeService.update(employeeSaved.getId(), employee);
  }

  @Test
  public void shouldFindAllEmployees() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    employeeService.save(employee);

    var employees = employeeService.findAll();
    assertThat(employees).isNotEmpty().size().isOne();
    assertThat(employees).first().extracting(EmployeePositionDTO::getName).isEqualTo(position.getName());
    assertThat(employees.get(0).getEmployees()).first().extracting(actualEmployee -> actualEmployee.getPerson().getName()).isEqualTo(candidate.getName());
  }

  @Test
  public void shouldFindEmptyEmployees() {
    var employees = employeeService.findAll();
    assertThat(employees).isEmpty();
  }

  @Test
  public void shouldDeleteById() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    var employedSaved = employeeService.save(employee);

    employeeService.deleteById(employedSaved.getId());

    var employees = employeeService.findAll();
    assertThat(employees).isEmpty();
  }

  @Test(expected = EmployeeNotFoundException.class)
  public void shouldNotDeleteById() {
    employeeService.deleteById(new Faker().number().randomNumber());
  }

  @Test
  public void shouldFindAllByPositionAndName() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    employeeService.save(employee);

    var employees = employeeService.findAllByPositionAndName(position.getName(), candidate.getName());

    assertThat(employees).isNotEmpty().size().isOne();
    assertThat(employees).first().extracting(employeeFound -> employeeFound.getPerson().getName()).isEqualTo(candidate.getName());
  }

  @Test
  public void shouldFindAllByPosition() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    employeeService.save(employee);

    var employees = employeeService.findAllByPositionAndName(position.getName(), null);

    assertThat(employees).isNotEmpty().size().isOne();
    assertThat(employees).first().extracting(employeeFound -> employeeFound.getPerson().getName()).isEqualTo(candidate.getName());
  }

  @Test
  public void shouldFindAllByName() {
    var employee = EmployeeDTOMother.random(candidate.getId(), position.getName());

    employeeService.save(employee);

    var employees = employeeService.findAllByPositionAndName(null, candidate.getName());

    assertThat(employees).isNotEmpty().size().isOne();
    assertThat(employees).first().extracting(employeeFound -> employeeFound.getPerson().getName()).isEqualTo(candidate.getName());
  }


}
