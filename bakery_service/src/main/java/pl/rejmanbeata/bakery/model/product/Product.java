package pl.rejmanbeata.bakery.model.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {
    private String name;
    private double price;
}
