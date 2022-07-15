package com.thomsonreuters.employcontrol.api.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.thomsonreuters.employcontrol.api.dto.ClientDTO;
import com.thomsonreuters.employcontrol.api.model.Client;
import com.thomsonreuters.employcontrol.api.service.ClientService;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientResource {

  private final ClientService clientService;

  public ClientResource(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping("/list")
  public List<ClientDTO> clientList() {
    return clientService.clientList();
  }

  @PostMapping("/create")
  public ResponseEntity<Client> create(
      @Valid @RequestBody ClientDTO clientDTO, HttpServletResponse response) {
    Client clientSave = clientService.create(clientDTO);

    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(clientSave.getId())
            .toUri();
    response.setHeader("Location", uri.toASCIIString());

    return ResponseEntity.created(uri).body(clientSave);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Client> searchForCode(@PathVariable Long id) {
    Optional<Client> client = clientService.searchForCode(id);
    return client.isPresent() ? ResponseEntity.ok(client.get()) : ResponseEntity.notFound().build();
  }
}
