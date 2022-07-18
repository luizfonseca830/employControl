package com.thomsonreuters.employcontrol.api.event;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

public class HeaderLocationEvent extends ApplicationEvent {

  transient private HttpServletResponse response;
  private Long id;

  public HeaderLocationEvent(Object source, HttpServletResponse response, Long id) {
    super(source);
    this.response = response;
    this.id = id;
  }

  public HttpServletResponse getResponse() {
    return response;
  }

  public Long getId() {
    return id;
  }
}
