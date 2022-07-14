package com.thomsonreuters.employcontrol.api.service;

import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.model.JobPosition;
import com.thomsonreuters.employcontrol.api.repository.JobPositionRepository;
import java.util.List;

@Service
public class JobPositionService {

  public final JobPositionRepository jobPositionRepository;

  public JobPositionService(JobPositionRepository jobPositionRepository){
    this.jobPositionRepository = jobPositionRepository;
  }

  public List<JobPosition> jobPositionList(){
    return jobPositionRepository.findAll();
  }

  public JobPosition create(JobPosition jobPosition){
    jobPosition.setId(null);
    return jobPositionRepository.save(jobPosition);
  }

}
