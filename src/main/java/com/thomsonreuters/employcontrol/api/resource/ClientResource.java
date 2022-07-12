package com.thomsonreuters.employcontrol.api.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

@RestController
@RequestMapping("/client")
public class ClientResource {

  private final ClientService clientService;

  public ClientResource(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping("/list")
  public List<Client> clientList() {
    return clientService.clientList();
  }

  @PostMapping("/create")
  public ResponseEntity<Client> create(@RequestBody ClientDTO clientDTO) {
    Client client = new Client();
    client.setName(clientDTO.getName());
    client = clientService.create(client);
    URI uri =
        ServletUriComponentsBuilder.fromCurrentRequestUri()
            .path("/{id}")
            .buildAndExpand(client.getId())
            .toUri();
    return ResponseEntity.created(uri).body(client);
  }
}
