package pl.rejmanbeata.bakery.model.client;

import lombok.Builder;
import lombok.Data;
import pl.rejmanbeata.bakery.model.address.Address;

@Data
@Builder
public class Client {
    private String name;
    private String lastName;
    private Address address;
}