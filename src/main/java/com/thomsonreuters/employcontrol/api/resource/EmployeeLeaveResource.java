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
import com.thomsonreuters.employcontrol.api.dto.EmployeeLeaveDTO;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.service.EmployeeLeaveService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/employeeLeave")
public class EmployeeLeaveResource {

  public EmployeeLeaveResource(EmployeeLeaveService employeeLeaveService) {
    this.employeeLeaveService = employeeLeaveService;
  }

  private final EmployeeLeaveService employeeLeaveService;

  @GetMapping("/list")
  public List<EmployeeLeaveDTO> listAll() {
    return employeeLeaveService.listAll();
  }

  @PostMapping("/create")
  public ResponseEntity<EmployeeLeave> create(
      @Valid @RequestBody EmployeeLeaveDTO employeeLeaveDTO, HttpServletResponse response) {
    EmployeeLeave employeeLeaveSave = employeeLeaveService.create(employeeLeaveDTO);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(employeeLeaveSave.getId())
            .toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(employeeLeaveSave);
  }
}