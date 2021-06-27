package com.test.human.resource.api.service;

import com.test.human.resource.api.model.dto.EmployeeDTO;
import com.test.human.resource.api.model.dto.EmployeeDetailDTO;
import com.test.human.resource.api.model.dto.EmployeePositionDTO;

import java.util.List;

public interface EmployeeService {

  EmployeeDetailDTO save(EmployeeDTO employee);

  EmployeeDetailDTO update(Long id, EmployeeDTO employee);

  List<EmployeeDetailDTO> findAllByPositionAndName(String positionName, String employeeName);

  void deleteById(Long id);

  List<EmployeePositionDTO> findAll();

}
