package com.thomsonreuters.employcontrol.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.thomsonreuters.employcontrol.api.enums.TypeContract;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "client")
  private Client client;

  @ManyToOne
  @JoinColumn(name = "jobposition")
  private JobPosition jobPosition;

  @Column(name = "name", nullable = false, length = 50 )
  @NotNull
  @Size(min = 10, max = 50)
  private String name;

  @Column(name = "national_identity", nullable = false, length = 20 )
  @NotNull
  @Size(max = 20)
  private String nationalIdentity;

  @Column(name = "birthdate")
  private LocalDate birthdate;

  @Column(name = "active")
  private Boolean active;

  @Column(name = "salary")
  private Float salary;

  @Column(name = "type_contract")
  @Enumerated(EnumType.STRING)
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
