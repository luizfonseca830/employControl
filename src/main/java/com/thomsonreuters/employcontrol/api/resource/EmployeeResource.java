package com.thomsonreuters.employcontrol.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.thomsonreuters.employcontrol.api.model.Employee;
import com.thomsonreuters.employcontrol.api.service.EmployeeService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {

  private final EmployeeService employeeService;

  public EmployeeResource(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/list")
  public List<Employee> employeeList() {
    return employeeService.employeeList();
  }

  @PostMapping("/create")
  public ResponseEntity<Employee> create(
      @Valid @RequestBody Employee employee, HttpServletResponse response) {
    Employee employeeSave = employeeService.create(employee);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(employeeSave.getId())
            .toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(employeeSave);
  }
}
