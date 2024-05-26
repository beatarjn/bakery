package pl.rejmanbeata.bakery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.jpa_repository.AddressRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;
    @InjectMocks
    private AddressService addressService;
    private AddressEntity address;
    private Random random = new Random();

    @BeforeEach
    public void setup() {
        address = AddressEntity.builder()
                .id(1L)
                .longitude(random.nextDouble())
                .latitude(random.nextDouble())
                .build();
    }

    @Test
    void testSave_shouldSaveAndReturnAddress() {
        given(addressRepository.save(address)).willReturn(address);

        AddressEntity savedAddress = addressService.save(address);

        assertThat(savedAddress).isNotNull();
    }

    @Test
    void testGetAllAddresses_shouldReturnAllAddresses() {
        AddressEntity address1 = AddressEntity.builder()
                .id(2L)
                .longitude(random.nextDouble())
                .latitude(random.nextDouble())
                .build();

        given(addressRepository.findAll()).willReturn(List.of(address, address1));

        List<AddressEntity> addresses = addressService.getAllAddresses();

        assertThat(addresses)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void testGetAllAddresses_shouldReturnEmptyList() {
        given(addressRepository.findAll()).willReturn(Collections.emptyList());

        List<AddressEntity> addresses = addressService.getAllAddresses();

        assertThat(addresses).isEmpty();
    }

    @Test
    void testGetAddressById_shouldReturnAddressById() {
        given(addressRepository.findById(1L)).willReturn(Optional.of(address));

        AddressEntity savedAddress = addressService.getAddressById(address.getId());

        assertThat(savedAddress).isNotNull();
    }

    @Test
    void testDelete_shouldDeleteAddressById() {
        long addressId = 1L;

        willDoNothing().given(addressRepository).deleteById(addressId);

        addressService.deleteAddressById(addressId);

        verify(addressRepository, times(1)).deleteById(addressId);
    }
}