package com.thomsonreuters.employcontrol.api.service;

import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.dto.JobPositionDTO;
import com.thomsonreuters.employcontrol.api.model.JobPosition;
import com.thomsonreuters.employcontrol.api.repository.JobPositionRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobPositionService {

  public JobPositionService(JobPositionRepository jobPositionRepository, ModelMapper modelMapper) {
    this.jobPositionRepository = jobPositionRepository;
    this.modelMapper = modelMapper;
  }

  public final JobPositionRepository jobPositionRepository;

  public final ModelMapper modelMapper;

  private JobPositionDTO convertToJobPosition(JobPosition jobPosition) {
    return modelMapper.map(jobPosition, JobPositionDTO.class);
  }

  private JobPosition convertToJobPositionDTO(JobPositionDTO jobPositionDTO) {
    return modelMapper.map(jobPositionDTO, JobPosition.class);
  }

  public List<JobPositionDTO> jobPositionList() {
    return jobPositionRepository.findAll().stream()
        .map(this::convertToJobPosition)
        .collect(Collectors.toList());
  }

  public JobPosition create(@Valid JobPositionDTO jobPositionDTO) {
    JobPosition jobPosition = convertToJobPositionDTO(jobPositionDTO);
    return jobPositionRepository.save(jobPosition);
  }
}
