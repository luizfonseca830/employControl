package com.thomsonreuters.employcontrol.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.thomsonreuters.employcontrol.api.model.Employee;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  Optional<Employee> findByNationalIdentity(String nationalIdentity);
}
