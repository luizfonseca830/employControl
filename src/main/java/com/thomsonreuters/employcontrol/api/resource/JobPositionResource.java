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
import com.thomsonreuters.employcontrol.api.dto.JobPositionDTO;
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
  public List<JobPositionDTO> jobPositionList() {
    return jobPositionService.jobPositionList();
  }

  @PostMapping("/create")
  public ResponseEntity<JobPosition> create(
     @Valid @RequestBody JobPositionDTO jobPositionDTO, HttpServletResponse response) {
    JobPosition jobPositionSave = jobPositionService.create(jobPositionDTO);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(jobPositionSave.getId())
            .toUri();
    response.setHeader("Location", uri.toASCIIString());
    return ResponseEntity.created(uri).body(jobPositionSave);
  }
}
