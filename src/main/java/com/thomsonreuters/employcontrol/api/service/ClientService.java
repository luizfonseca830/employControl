package com.thomsonreuters.employcontrol.api.service;

import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.model.Client;
import com.thomsonreuters.employcontrol.api.repository.ClientRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public List<Client> clientList() {
    return clientRepository.findAll();
  }

  public Client create(Client client) {
    client.setId(null);
    return clientRepository.save(client);
  }

  public Optional<Client> searchForCode(Long id) {
    return clientRepository.findById(id);
  }
}
