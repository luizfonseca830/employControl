package com.thomsonreuters.employcontrol.api.listerner;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.thomsonreuters.employcontrol.api.event.HeaderLocationEvent;
import java.net.URI;

@Component
public class HeaderLocationListener implements ApplicationListener<HeaderLocationEvent> {

  /**
   * @param headerLocationEvent the event to respond to
   */
  @Override
  public void onApplicationEvent(HeaderLocationEvent headerLocationEvent) {
    HttpServletResponse response = headerLocationEvent.getResponse();
    Long id = headerLocationEvent.getId();

    addHeaderLocation(response, id);
  }

  private void addHeaderLocation(HttpServletResponse response, Long id) {
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(id)
            .toUri();
    response.setHeader("Location", uri.toASCIIString());
  }
}
