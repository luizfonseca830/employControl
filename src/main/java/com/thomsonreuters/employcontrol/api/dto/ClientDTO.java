package com.thomsonreuters.employcontrol.api.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClientDTO implements Serializable {

  private Long id;

  @NotNull
  @Size(min = 10, max = 30)
  private String name;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
