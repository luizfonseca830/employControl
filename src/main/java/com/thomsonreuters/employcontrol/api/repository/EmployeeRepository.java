package com.thomsonreuters.employcontrol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thomsonreuters.employcontrol.api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
