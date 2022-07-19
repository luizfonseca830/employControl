package com.thomsonreuters.employcontrol.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thomsonreuters.employcontrol.api.dto.EmployeeLeaveDTO;
import com.thomsonreuters.employcontrol.api.event.HeaderLocationEvent;
import com.thomsonreuters.employcontrol.api.exceptionhandler.ExceptionHandler.Erro;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.service.EmployeeLeaveService;
import com.thomsonreuters.employcontrol.api.service.exception.EmployeeLeaveTypeLeaveException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employeeLeave")
public class EmployeeLeaveResource {

  public EmployeeLeaveResource(
      EmployeeLeaveService employeeLeaveService,
      ApplicationEventPublisher publisher,
      MessageSource messageSource) {
    this.employeeLeaveService = employeeLeaveService;
    this.publisher = publisher;
    this.messageSource = messageSource;
  }

  private final EmployeeLeaveService employeeLeaveService;

  private final ApplicationEventPublisher publisher;

  private final MessageSource messageSource;

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

  @PutMapping("/edit/{id}")
  public EmployeeLeave edit(@PathVariable Long id, @RequestBody EmployeeLeaveDTO employeeLeaveDTO) {
    return this.employeeLeaveService.edit(employeeLeaveDTO, id);
  }

  @ExceptionHandler({EmployeeLeaveTypeLeaveException.class})
  public ResponseEntity<Object> handleEmployeeDeathException(EmployeeLeaveTypeLeaveException ex) {
    String messageUser =
        messageSource.getMessage(
            "employeeLeave.TypeLeave",
            null,
            LocaleContextHolder.getLocale());
    String messageDevelope = ex.toString();
    List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDevelope));
    return ResponseEntity.badRequest().body(erros);
  }
}
