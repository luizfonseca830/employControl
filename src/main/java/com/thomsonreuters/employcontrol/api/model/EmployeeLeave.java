package com.thomsonreuters.employcontrol.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thomsonreuters.employcontrol.api.enums.Type;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;

@Entity
@Table(name = "employee_leave")
public class EmployeeLeave implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "client", nullable = false)
  private Client client;

  @ManyToOne
  @JoinColumn(name = "employee", nullable = false)
  private Employee employee;

  @Column(name = "leave_type", nullable = false, length = 20)
  private String leaveType;

  @Column(name = "leave_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate leaveDate;

  @Column(name = "return_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate returnDate;

  @Column(name = "number_days")
  private Integer numberDays;

  @Column(name = "type")
  private Type type;

  public Integer getNumberDays() {
    return numberDays;
  }

  public void setNumberDays(Integer numberDays) {
    this.numberDays = numberDays;
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

  public TemporalAccessor setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
    return returnDate;
  }

  public String getLeaveType() {
    return leaveType;
  }

  public void setLeaveType(String leaveType) {
    this.leaveType = leaveType;
  }

  public Employee getEmployee() {
    return employee;
  }

  public void setEmployee(Employee employee) {
    this.employee = employee;
  }

  public Client getClient() {
    return client;
  }

  public void setClient(Client client) {
    this.client = client;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
