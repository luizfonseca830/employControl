package com.thomsonreuters.employcontrol.api.service;

import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.dto.JobPositionDTO;
import com.thomsonreuters.employcontrol.api.model.JobPosition;
import com.thomsonreuters.employcontrol.api.repository.JobPositionRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPositionService {

  public final JobPositionRepository jobPositionRepository;

  public JobPositionService(JobPositionRepository jobPositionRepository) {
    this.jobPositionRepository = jobPositionRepository;
  }

  private JobPositionDTO toJobPositionDTO(JobPosition jobPosition) {
    var jobPositionDTO = new JobPositionDTO();
    jobPositionDTO.setId(jobPosition.getId());
    jobPositionDTO.setName(jobPosition.getName());
    return jobPositionDTO;
  }

  public List<JobPositionDTO> jobPositionList() {
    return jobPositionRepository.findAll().stream()
        .map(this::toJobPositionDTO)
        .collect(Collectors.toList());
  }

  public JobPosition create(JobPosition jobPosition) {
    jobPosition.setId(null);
    return jobPositionRepository.save(jobPosition);
  }
}
