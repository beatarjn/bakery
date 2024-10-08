package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.jpa_repository.ClientRepository;
import pl.rejmanbeata.bakery.mapper.ClientMapper;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public Client save(ClientEntity clientEntity) {
        ClientEntity savedClient = clientRepository.save(clientEntity);
        return clientMapper.clientEntityToClient(savedClient);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::clientEntityToClient)
                .toList();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::clientEntityToClient)
                .orElse(null);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public Client findByLastName(String lastName) {
        return clientMapper.clientEntityToClient(clientRepository.findByLastName(lastName));
    }

    public Client updateClient(Long clientId, ClientEntity client) {
        Optional<ClientEntity> existingClient = clientRepository.findById(clientId);
        if (existingClient.isPresent()) {
            ClientEntity clientEntity = existingClient.get();
            clientEntity.setLastName(client.getLastName());
            clientEntity.setName(client.getName());
            clientEntity.setAddress(client.getAddress());
            ClientEntity savedClient = clientRepository.save(clientEntity);
            return clientMapper.clientEntityToClient(savedClient);
        }
        return null;
    }
}
