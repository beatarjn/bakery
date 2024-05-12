package pl.rejmanbeata.bakery.jpa_repository;

import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.database.ProductEntity;

import java.util.Random;

public class TestHelper {

    private static final Random random = new Random();

    public static AddressEntity generateAddressEntity() {
        AddressEntity address = new AddressEntity();
        address.setLatitude(random.nextDouble());
        address.setLongitude(random.nextDouble());
        return address;
    }

    public static OrderEntity createOrderEntity(ProductEntity product, Integer quantity) {
        OrderEntity order = new OrderEntity();
        order.setProduct(product);
        order.setQuantity(quantity);
        return order;
    }
}
