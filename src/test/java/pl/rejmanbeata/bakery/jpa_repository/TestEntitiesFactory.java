package pl.rejmanbeata.bakery.jpa_repository;

import pl.rejmanbeata.bakery.database.AddressEntity;
import pl.rejmanbeata.bakery.database.EmployeeEntity;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.database.ProductEntity;

import java.util.Random;

public class TestEntitiesFactory {

    private static final Random random = new Random();

    public static AddressEntity createAddressEntity() {
        return AddressEntity.builder()
                .latitude(random.nextDouble())
                .longitude(random.nextDouble())
                .build();
    }

    public static OrderEntity createOrderEntity(ProductEntity product, Integer quantity) {
        return OrderEntity.builder()
                .product(product)
                .quantity(quantity)
                .build();
    }

    public static EmployeeEntity createEmployeeEntityWithRoleAndAddress(String role, AddressEntity address) {
        return EmployeeEntity.builder()
                .name("John")
                .lastName("Doe")
                .address(address)
                .role(role)
                .build();
    }

    public static ProductEntity createProductEntity(String name, Double price) {
        return ProductEntity.builder()
                .price(price)
                .name(name)
                .build();
    }

}