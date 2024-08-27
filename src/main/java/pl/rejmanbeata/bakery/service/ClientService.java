package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.jpa_repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientEntity save(ClientEntity clientEntity) {
        return clientRepository.save(clientEntity);
    }

    public List<ClientEntity> getAllClients() {
        return clientRepository.findAll();
    }

    public ClientEntity getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientEntity findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    public ClientEntity updateClient(Long clientId, ClientEntity client) {
        Optional<ClientEntity> existingClient = clientRepository.findById(clientId);
        if (existingClient.isPresent()) {
            existingClient.get().setLastName(client.getLastName());
            existingClient.get().setName(client.getName());
            existingClient.get().setAddress(client.getAddress());
            return clientRepository.save(existingClient.get());
        }
        return null;
    }
}
