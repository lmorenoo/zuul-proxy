package com.test.human.resource.api.service.impl;

import com.test.human.resource.api.exception.CandidateNotFoundException;
import com.test.human.resource.api.exception.EmployeeAlreadyExistsException;
import com.test.human.resource.api.exception.EmployeeNotFoundException;
import com.test.human.resource.api.exception.PositionNotFoundException;
import com.test.human.resource.api.model.Candidate;
import com.test.human.resource.api.model.Employee;
import com.test.human.resource.api.model.Position;
import com.test.human.resource.api.model.dto.EmployeeDTO;
import com.test.human.resource.api.model.dto.EmployeeDetailDTO;
import com.test.human.resource.api.model.dto.EmployeePositionDTO;
import com.test.human.resource.api.repository.EmployeeRepository;
import com.test.human.resource.api.service.CandidateService;
import com.test.human.resource.api.service.EmployeeService;
import com.test.human.resource.api.service.PositionService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    public static final String SALARY = "salary";
    private final EmployeeRepository employeeRepository;

    private final PositionService positionService;

    private final CandidateService candidateService;

    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PositionService positionService,
                               CandidateService candidateService, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.positionService = positionService;
        this.candidateService = candidateService;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDetailDTO save(EmployeeDTO employee) {
        if (employeeRepository.existsByPersonId(employee.getPersonId())) {
            throw new EmployeeAlreadyExistsException(employee.getPersonId());
        }
        return saveEmployee(employee);
    }


    @Override
    public EmployeeDetailDTO update(Long id, EmployeeDTO employee) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employee.setId(id);
        return saveEmployee(employee);
    }

    @Override
    public List<EmployeeDetailDTO> findAllByPositionAndName(String positionName, String employeeName) {
        var position = new Position();
        position.setName(positionName);
        var candidate = new Candidate();
        candidate.setName(employeeName);

        var employee = new Employee();
        employee.setPosition(position);
        employee.setPerson(candidate);

        var employees = employeeRepository.findAll(Example.of(employee));
        return modelMapper.map(employees, new TypeToken<List<EmployeeDetailDTO>>() {
        }.getType());
    }

    @Override
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeePositionDTO> findAll() {
        Sort sort = Sort.by(Sort.Order.desc(SALARY));
        var employees = employeeRepository.findAll(sort);
        var employeesMap = employees.stream().collect(Collectors.groupingBy(Employee::getPosition));

        return employeesMap.entrySet().stream().map(resultSet -> new EmployeePositionDTO(resultSet.getKey(),
                resultSet.getValue())).collect(Collectors.toList());
    }

    private EmployeeDetailDTO saveEmployee(EmployeeDTO employee) {
        var position = positionService.findByName(employee.getPositionName())
                .orElseThrow(() -> new PositionNotFoundException(employee.getPositionName()));
        var candidate = candidateService.findById(employee.getPersonId())
                .orElseThrow(() -> new CandidateNotFoundException(employee.getPersonId()));

        var newEmployee = modelMapper.map(employee, Employee.class);
        newEmployee.setPosition(position);
        newEmployee.setPerson(candidate);
        var employeeSaved = employeeRepository.save(newEmployee);
        return new EmployeeDetailDTO(employeeSaved);
    }
}
