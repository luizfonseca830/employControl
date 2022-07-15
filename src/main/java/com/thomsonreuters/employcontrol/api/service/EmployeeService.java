package com.thomsonreuters.employcontrol.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.dto.EmployeeDTO;
import com.thomsonreuters.employcontrol.api.model.Employee;
import com.thomsonreuters.employcontrol.api.repository.EmployeeRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  private final ModelMapper modelMapper;

  public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
    this.employeeRepository = employeeRepository;
    this.modelMapper = modelMapper;
  }

  private EmployeeDTO convertToEmployee(Employee employee){
    return modelMapper.map(employee, EmployeeDTO.class);
  }

  private Employee convertToEmployeeDTO(EmployeeDTO employeeDTO){
    return modelMapper.map(employeeDTO, Employee.class);
  }

  public List<EmployeeDTO> employeeList(){
    return employeeRepository.findAll().stream().map(this::convertToEmployee).collect(Collectors.toList());
  }

  public Employee create(EmployeeDTO employeeDTO){
    Employee employee = convertToEmployeeDTO(employeeDTO);
    return employeeRepository.save(employee);
  }

}
