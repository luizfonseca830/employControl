package com.thomsonreuters.employcontrol.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.thomsonreuters.employcontrol.api.enums.TypeContract;
import com.thomsonreuters.employcontrol.api.model.Client;
import com.thomsonreuters.employcontrol.api.model.JobPosition;
import java.time.LocalDate;

public class EmployeeDTO {

  private Long id;
  private Client client;
  private JobPosition jobPosition;

  @NotNull
  @Size(min = 10, max = 50)
  private String name;

  @NotNull
  @Size(max = 20)
  private String nationalIdentity;

  private LocalDate birthdate;
  private Boolean active;
  private Float salary;
  private TypeContract typeContract;

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

  public JobPosition getJobPosition() {
    return jobPosition;
  }

  public void setJobPosition(JobPosition jobPosition) {
    this.jobPosition = jobPosition;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNationalIdentity() {
    return nationalIdentity;
  }

  public void setNationalIdentity(String nationalIdentity) {
    this.nationalIdentity = nationalIdentity;
  }

  public LocalDate getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDate birthdate) {
    this.birthdate = birthdate;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Float getSalary() {
    return salary;
  }

  public void setSalary(Float salary) {
    this.salary = salary;
  }

  public TypeContract getTypeContract() {
    return typeContract;
  }

  public void setTypeContract(TypeContract typeContract) {
    this.typeContract = typeContract;
  }
}
