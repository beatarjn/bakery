package pl.rejmanbeata.bakery.jpa_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.rejmanbeata.bakery.database.AddressEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pl.rejmanbeata.bakery.jpa_repository.TestHelper.generateAddressEntity;

@ActiveProfiles("test")
@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    void shouldFindAll() {
        List<AddressEntity> allAddresses = addressRepository.findAll();

        assertNotNull(allAddresses);
        assertEquals(5, allAddresses.size());
    }

    @Test
    void shouldSaveAddress() {
        AddressEntity addressEntity = generateAddressEntity();

        List<AddressEntity> allAddresses = addressRepository.findAll();
        assertNotNull(allAddresses);

        addressRepository.save(addressEntity);

        List<AddressEntity> allAddressesAfterSave = addressRepository.findAll();

        assertThat(allAddressesAfterSave).hasSize(allAddresses.size() + 1);
    }


    @Test
    void shouldSaveAndFindAddressById() {
        AddressEntity addressEntity = generateAddressEntity();

        AddressEntity saved = addressRepository.save(addressEntity);

        Optional<AddressEntity> foundAddress = addressRepository.findById(saved.getId());

        assertThat(foundAddress)
                .isPresent()
                .containsSame(addressEntity);
    }

}