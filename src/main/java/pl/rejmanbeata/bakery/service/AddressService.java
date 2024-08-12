package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.jpa_repository.AddressRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressEntity save(AddressEntity address) {
        return addressRepository.save(address);
    }

    public List<AddressEntity> getAllAddresses() {
        return addressRepository.findAll();
    }

    public AddressEntity getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }
}
