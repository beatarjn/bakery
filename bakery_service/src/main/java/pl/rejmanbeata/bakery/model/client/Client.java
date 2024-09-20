package pl.rejmanbeata.bakery.model.client;

import lombok.*;
import pl.rejmanbeata.bakery.model.address.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString()
@EqualsAndHashCode()
@Builder
public class Client {
    private String name;
    private String lastName;
    private Address address;
}