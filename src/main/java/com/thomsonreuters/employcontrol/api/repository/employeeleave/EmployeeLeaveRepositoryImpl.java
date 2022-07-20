package com.thomsonreuters.employcontrol.api.repository.employeeleave;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.util.ObjectUtils;
import com.thomsonreuters.employcontrol.api.model.EmployeeLeave;
import com.thomsonreuters.employcontrol.api.repository.filter.EmployeeLeaveFilter;
import java.util.ArrayList;
import java.util.List;

public class EmployeeLeaveRepositoryImpl implements EmployeeLeaveRepositoryQuery {

  @PersistenceContext private EntityManager manager;
  /**
   * @param employeeLeaveFilter
   * @return
   */
  @Override
  public List<EmployeeLeave> filter(EmployeeLeaveFilter employeeLeaveFilter) {
    CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
    CriteriaQuery<EmployeeLeave> criteria = criteriaBuilder.createQuery(EmployeeLeave.class);
    Root<EmployeeLeave> root = criteria.from(EmployeeLeave.class);

    Predicate[] predicates = createRestrictions(employeeLeaveFilter, criteriaBuilder, root);
    criteria.where(predicates);

    TypedQuery<EmployeeLeave> query = manager.createQuery(criteria);
    return query.getResultList();
  }

  private Predicate[] createRestrictions(
      EmployeeLeaveFilter employeeLeaveFilter,
      CriteriaBuilder criteriaBuilder,
      Root<EmployeeLeave> root) {
    List<Predicate> predicates = new ArrayList<>();

    if (!ObjectUtils.isEmpty(employeeLeaveFilter.getLeaveDate())) {
      predicates.add(
          criteriaBuilder.like(
              criteriaBuilder.lower(root.get("leaveDate")),
              "%" + employeeLeaveFilter.getLeaveDate().toString() + "%"));
    }
    if (!ObjectUtils.isEmpty(employeeLeaveFilter.getReturnDate())) {
      predicates.add(
          criteriaBuilder.like(
              criteriaBuilder.lower(root.get("returnDate")),
              "%" + employeeLeaveFilter.getReturnDate().toString() + "%"));
    }
    if (!ObjectUtils.isEmpty(employeeLeaveFilter.getLeaveType())) {
      predicates.add(
          criteriaBuilder.like(
              criteriaBuilder.lower(root.get("leaveType")),
              "%" + employeeLeaveFilter.getLeaveType().toString() + "%"));
    }

    if (!ObjectUtils.isEmpty(employeeLeaveFilter.getEmployee())) {
      predicates.add(
          criteriaBuilder.like(
              criteriaBuilder.lower(root.get("employee").get("name")),
              "%" + employeeLeaveFilter.getEmployee().getName() + "%"));
    }

    return predicates.toArray(new Predicate[predicates.size()]);
  }
}
