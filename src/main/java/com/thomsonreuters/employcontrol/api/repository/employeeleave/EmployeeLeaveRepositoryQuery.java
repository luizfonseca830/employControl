package com.thomsonreuters.employcontrol.api.repository.employeeleave;

import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.repository.filter.EmployeeLeaveFilter;
import java.util.List;

public interface EmployeeLeaveRepositoryQuery {
  List<EmployeeLeave> filter(EmployeeLeaveFilter employeeLeaveFilter);
}
