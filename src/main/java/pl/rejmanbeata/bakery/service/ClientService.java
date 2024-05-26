package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.ClientEntity;
import pl.rejmanbeata.bakery.jpa_repository.ClientRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public void save(ClientEntity address) {
        clientRepository.save(address);
    }

    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    public ClientEntity findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public ClientEntity findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }
}
