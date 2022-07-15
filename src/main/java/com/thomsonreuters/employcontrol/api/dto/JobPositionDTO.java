package com.thomsonreuters.employcontrol.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class JobPositionDTO {

  private Long id;

  @NotNull
  @Size(max = 30)
  private String name;

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
