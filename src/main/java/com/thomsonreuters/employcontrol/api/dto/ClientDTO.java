package com.thomsonreuters.employcontrol.api.dto;

import com.thomsonreuters.employcontrol.api.model.Client;
import java.io.Serializable;

public class ClientDTO implements Serializable {

  private static final long serialVersionUID = 1L;
  private Long id;
  private String name;

  public ClientDTO(Client obj) {
    this.id = obj.getId();
    this.name = obj.getName();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
