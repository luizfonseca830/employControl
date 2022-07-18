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
import com.thomsonreuters.employcontrol.api.dto.EmployeeLeaveDTO;
import com.thomsonreuters.employcontrol.api.event.HeaderLocationEvent;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.service.EmployeeLeaveService;
import java.util.List;

@RestController
@RequestMapping("/employeeLeave")
public class EmployeeLeaveResource {

  public EmployeeLeaveResource(
      EmployeeLeaveService employeeLeaveService, ApplicationEventPublisher publisher) {
    this.employeeLeaveService = employeeLeaveService;
    this.publisher = publisher;
  }

  private final EmployeeLeaveService employeeLeaveService;

  private final ApplicationEventPublisher publisher;

  @GetMapping("/list")
  public List<EmployeeLeaveDTO> listAll() {
    return employeeLeaveService.listAll();
  }

  @PostMapping("/create")
  public ResponseEntity<EmployeeLeave> create(
      @Valid @RequestBody EmployeeLeaveDTO employeeLeaveDTO, HttpServletResponse response) {
    EmployeeLeave employeeLeaveSave = employeeLeaveService.create(employeeLeaveDTO);
    publisher.publishEvent(new HeaderLocationEvent(this, response, employeeLeaveSave.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(employeeLeaveSave);
  }
}
