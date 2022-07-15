package com.thomsonreuters.employcontrol.api.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thomsonreuters.employcontrol.api.dto.ClientDTO;
import com.thomsonreuters.employcontrol.api.model.Client;
import com.thomsonreuters.employcontrol.api.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  private final ModelMapper modelMapper;

  public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
    this.clientRepository = clientRepository;
    this.modelMapper = modelMapper;
  }

  private Client convetToClientDTO(ClientDTO clientDTO){
    return modelMapper.map(clientDTO, Client.class);
//    MODO ANTIGO SEM MODELMAPPER
//    Client client = new Client();
//    client.setId(clientDTO.getId());
//    client.setName(clientDTO.getName());
//    return client;
  }
  private ClientDTO convetToClient(Client client) {
    return modelMapper.map(client, ClientDTO.class);
  }

  public List<ClientDTO> clientList() {
    return clientRepository.findAll().stream().map(this::convetToClient).collect(Collectors.toList());
  }

  public ClientDTO create(ClientDTO clientDTO) {
    Client client = convetToClientDTO(clientDTO);
    Client clientCreate = clientRepository.save(client);
    return convetToClient(clientCreate);
  }

  public Optional<Client> searchForCode(Long id) {
    return clientRepository.findById(id);
  }
}
