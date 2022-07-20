package com.thomsonreuters.employcontrol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.repository.employeeleave.EmployeeLeaveRepositoryQuery;

public interface EmployeeLeaveRepository
    extends JpaRepository<EmployeeLeave, Long>, EmployeeLeaveRepositoryQuery {}
