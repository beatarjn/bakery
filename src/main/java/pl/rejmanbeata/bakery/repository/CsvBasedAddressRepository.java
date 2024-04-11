package pl.rejmanbeata.bakery.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.rejmanbeata.bakery.model.Address;

import java.util.List;
import java.util.Optional;

import static pl.rejmanbeata.bakery.repository.FileReader.readCsv;

@Repository
@AllArgsConstructor
public class CsvBasedAddressRepository implements AddressRepository {

    @Override
    public Address findById(String id) {

        List<List<String>> values = readCsv("files/address.csv");

        Optional<List<String>> address = values.stream()
                .filter(l -> l.get(0).equals(id))
                .findFirst();

        return address
                .map(a -> buildAddress(a.get(1), a.get(2)))
                .orElseThrow(() -> new IllegalArgumentException("Address with id " + id + " not found"));
    }

    private Address buildAddress(String longitude, String latitude) {
        return Address.builder()
                .longitude(Double.parseDouble(longitude))
                .latitude(Double.parseDouble(latitude))
                .build();
    }
}