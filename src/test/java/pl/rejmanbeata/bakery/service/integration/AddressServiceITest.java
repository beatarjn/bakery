package pl.rejmanbeata.bakery.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.jpa_repository.AddressRepository;
import pl.rejmanbeata.bakery.service.AddressService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createAddressEntity;

@SpringBootTest
class AddressServiceITest {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void testFindAddressById_shouldReturnAddress() {
        AddressEntity foundAddress = addressService.getAddressById(1L);

        assertNotNull(foundAddress);
    }

    @Test
    void testFindAddressById_shouldReturnNull() {
        AddressEntity foundAddress = addressService.getAddressById(8L);

        assertNull(foundAddress);
    }

    @Test
    void testGetAllAddresses_shouldFindAll() {
        List<AddressEntity> allAddresses = addressService.getAllAddresses();

        assertNotNull(allAddresses);
        assertEquals(5, allAddresses.size());
    }

    @Test
    void testSave_shouldSaveAddress() {
        AddressEntity addressEntity = createAddressEntity();

        List<AddressEntity> allAddresses = addressService.getAllAddresses();
        assertNotNull(allAddresses);

        addressRepository.save(addressEntity);

        List<AddressEntity> allAddressesAfterSave = addressService.getAllAddresses();

        assertThat(allAddressesAfterSave).hasSize(allAddresses.size() + 1);
    }

//    @Test
//    void testDeleteById_shouldDeleteAddress() {
//        AddressEntity addressEntity = createAddressEntity();
//
//
//
//        addressService.save(addressEntity);
//        List<AddressEntity> allAddresses = addressService.getAllAddresses();
//        assertNotNull(allAddresses);
//
//        addressService.deleteAddressById(addressEntity.getId());
//
//        List<AddressEntity> allAddressesAfterDelete = addressService.getAllAddresses();
//
////        assertThat(allAddressesAfterDelete).hasSize(allAddresses.size() - 1);
//        assertNull(addressService.getAddressById(addressEntity.getId()));
//    }


}
