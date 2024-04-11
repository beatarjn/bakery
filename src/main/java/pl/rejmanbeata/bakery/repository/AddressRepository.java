package pl.rejmanbeata.bakery.repository;

import pl.rejmanbeata.bakery.model.Address;

public interface AddressRepository {

    Address findById(String id);
}
