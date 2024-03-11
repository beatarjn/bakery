package pl.rejmanbeata.bakery.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.rejmanbeata.bakery.model.Address;
import pl.rejmanbeata.bakery.model.client.Client;

import java.util.List;
import java.util.Optional;

import static pl.rejmanbeata.bakery.repository.FileReader.readCsv;

@Repository
@AllArgsConstructor
public class CsvBasedClientRepository implements ClientRepository {

    private AddressRepository addressRepository;

    @Override
    public List<Client> findAll() {
        return null;
    }

    @Override
    public Client findByName(String name) {

        List<List<String>> clients = readCsv("files/clients.csv");

        Optional<Client> clientByName = clients.stream()
                .filter(c -> c.get(0).equals(name))
                .map(c -> {
                    String clientName = c.get(0);
                    String lastName = c.get(1);
                    String addressId = c.get(2);
                    Address addressById = addressRepository.findById(addressId);
                    return createClient(clientName, lastName, addressById);
                })
                .findFirst();

        return clientByName.orElseThrow(() -> new IllegalArgumentException("Problems with finding client. \n"));
    }

    @Override
    public void saveNewClient(String name, String lastName, Address address) {

    }

    @Override
    public void deleteClient(String name) {
    }

    @Override
    public Client updateClient(String name, String newName, String newLastName, Address newAddress) {
        return null;
    }


    private static Client createClient(String name, String lastName, Address address) {
        return Client.builder()
                .name(name)
                .lastName(lastName)
                .address(address)
                .build();
    }
}