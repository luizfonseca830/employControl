package com.thomsonreuters.employcontrol.api.dto;

import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thomsonreuters.employcontrol.api.enums.LeaveType;
import com.thomsonreuters.employcontrol.api.model.Client;
import com.thomsonreuters.employcontrol.api.model.Employee;
import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeLeaveDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private Long id;
  @ManyToOne private Client client;
  @ManyToOne private Employee employee;

  private LeaveType leaveType;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate leaveDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate returnDate;

  private Integer numberDays;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public LeaveType getLeaveType() {
    return leaveType;
  }

  public void setLeaveType(LeaveType leaveType) {
    this.leaveType = leaveType;
  }

  public LocalDate getLeaveDate() {
    return leaveDate;
  }

  public void setLeaveDate(LocalDate leaveDate) {
    this.leaveDate = leaveDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }

  public Integer getNumberDays() {
    return numberDays;
  }

  public void setNumberDays(Integer numberDays) {
    this.numberDays = numberDays;
  }
}
