package com.thomsonreuters.employcontrol.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thomsonreuters.employcontrol.api.dto.ClientDTO;
import com.thomsonreuters.employcontrol.api.event.HeaderLocationEvent;
import com.thomsonreuters.employcontrol.api.model.Client;
import com.thomsonreuters.employcontrol.api.service.ClientService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientResource {

  private final ClientService clientService;

  private final ApplicationEventPublisher publisher;

  public ClientResource(ClientService clientService, ApplicationEventPublisher publisher) {
    this.clientService = clientService;
    this.publisher = publisher;
  }

  @GetMapping("/list")
  public List<ClientDTO> clientList() {
    return clientService.clientList();
  }

  @PostMapping("/create")
  public ResponseEntity<Client> create(
      @Valid @RequestBody ClientDTO clientDTO, HttpServletResponse response) {
    Client clientSave = clientService.create(clientDTO);
    publisher.publishEvent(new HeaderLocationEvent(this, response, clientSave.getId()));
    return ResponseEntity.status(HttpStatus.CREATED).body(clientSave);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Client> searchForCode(@PathVariable Long id) {
    Optional<Client> client = clientService.searchForCode(id);
    return client.isPresent() ? ResponseEntity.ok(client.get()) : ResponseEntity.notFound().build();
  }

  @PutMapping("/edit/{id}")
  public Client edit(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
    Client clientSalvo =
        this.clientService
            .searchForCode(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
    BeanUtils.copyProperties(clientDTO, clientSalvo, "id");
    return this.clientService.edit(clientSalvo);
  }
}
