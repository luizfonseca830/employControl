package com.thomsonreuters.employcontrol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;

public interface EmployeeLeaveRepository extends JpaRepository<EmployeeLeave, Integer> {}
