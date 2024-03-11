package pl.rejmanbeata.bakery.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Address {

    private double longitude;
    private double latitude;

}
