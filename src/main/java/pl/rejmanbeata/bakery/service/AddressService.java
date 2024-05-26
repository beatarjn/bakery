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

    public void save(AddressEntity address) {
        addressRepository.save(address);
    }

    public List<AddressEntity> findAll() {
        return addressRepository.findAll();
    }

    public AddressEntity findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
