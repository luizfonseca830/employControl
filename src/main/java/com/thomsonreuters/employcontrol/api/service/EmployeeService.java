package com.thomsonreuters.employcontrol.api.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.model.Employee;
import com.thomsonreuters.employcontrol.api.repository.EmployeeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  private final EmployeeRepository employeeRepository;

  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  public List<Employee> employeeList(){
    return employeeRepository.findAll();
  }

  public Employee create(Employee employee){
    return employeeRepository.save(employee);
  }

}
