package com.thomsonreuters.employcontrol.api.resource;

import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.thomsonreuters.employcontrol.api.model.JobPosition;
import com.thomsonreuters.employcontrol.api.service.JobPositionService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/jobposition")
public class JobPositionResource {

  private final JobPositionService jobPositionService;

  public JobPositionResource(JobPositionService jobPositionService) {
    this.jobPositionService = jobPositionService;
  }

  @GetMapping("/list")
  public List<JobPosition> jobPositionList() {
    return jobPositionService.jobPositionList();
  }

  @PostMapping("/create")
  public ResponseEntity<JobPosition> create(
      @RequestBody JobPosition jobPosition, HttpServletResponse response) {
    JobPosition jobPositionSave = jobPositionService.create(jobPosition);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(jobPositionSave.getId())
            .toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(jobPositionSave);
  }
}
