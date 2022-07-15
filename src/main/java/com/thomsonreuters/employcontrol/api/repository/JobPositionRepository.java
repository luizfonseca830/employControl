package com.thomsonreuters.employcontrol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thomsonreuters.employcontrol.api.model.JobPosition;

public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {}
