package pl.rejmanbeata.bakery.repository;

import pl.rejmanbeata.bakery.model.address.Address;

public interface AddressRepository {

    Address findById(String id);
}
