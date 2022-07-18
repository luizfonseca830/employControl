package com.thomsonreuters.employcontrol.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thomsonreuters.employcontrol.api.dto.EmployeeDTO;
import com.thomsonreuters.employcontrol.api.event.HeaderLocationEvent;
import com.thomsonreuters.employcontrol.api.model.Employee;
import com.thomsonreuters.employcontrol.api.service.EmployeeService;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

  private final EmployeeService employeeService;

  private final ApplicationEventPublisher publisher;

  public EmployeeResource(EmployeeService employeeService, ApplicationEventPublisher publisher) {
    this.employeeService = employeeService;
    this.publisher = publisher;
  }

  @GetMapping("/list")
  public List<EmployeeDTO> employeeList() {
    return employeeService.employeeList();
  }

  @PostMapping("/create")
  public ResponseEntity<Employee> create(
      @Valid @RequestBody EmployeeDTO employeeDTO, HttpServletResponse response) {
    Employee employeeSave = employeeService.create(employeeDTO);
    publisher.publishEvent(new HeaderLocationEvent(this, response, employeeSave.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeSave);
  }
}
