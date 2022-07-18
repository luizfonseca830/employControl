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
import com.thomsonreuters.employcontrol.api.dto.JobPositionDTO;
import com.thomsonreuters.employcontrol.api.event.HeaderLocationEvent;
import com.thomsonreuters.employcontrol.api.model.JobPosition;
import com.thomsonreuters.employcontrol.api.service.JobPositionService;
import java.util.List;

@RestController
@RequestMapping("/jobposition")
public class JobPositionResource {

  private final JobPositionService jobPositionService;

  private final ApplicationEventPublisher publisher;

  public JobPositionResource(
      JobPositionService jobPositionService, ApplicationEventPublisher publisher) {
    this.jobPositionService = jobPositionService;
    this.publisher = publisher;
  }

  @GetMapping("/list")
  public List<JobPositionDTO> jobPositionList() {
    return jobPositionService.jobPositionList();
  }

  @PostMapping("/create")
  public ResponseEntity<JobPosition> create(
      @Valid @RequestBody JobPositionDTO jobPositionDTO, HttpServletResponse response) {
    JobPosition jobPositionSave = jobPositionService.create(jobPositionDTO);
    publisher.publishEvent(new HeaderLocationEvent(this, response, jobPositionSave.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(jobPositionSave);
  }
}
