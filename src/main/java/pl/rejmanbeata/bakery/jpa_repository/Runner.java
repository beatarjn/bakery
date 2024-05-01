package pl.rejmanbeata.bakery.jpa_repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.rejmanbeata.bakery.BakeryApplication;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.database.ProductEntity;

@SpringBootApplication
@EnableJpaRepositories
public class Runner {

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(BakeryApplication.class, args);

        OrderRepository order = app.getBean(OrderRepository.class);

        System.out.println("Find all orders: ");
        order.findAll().forEach(System.out::println);

        System.out.println("Find order by id: ");
        order.findById(1L).ifPresent(System.out::println);

        System.out.println("Does order with id 4 exists? " + order.existsById(4L));

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setProduct(new ProductEntity("Donut", 5.5));
        orderEntity.setQuantity(3);
        order.save(orderEntity);

        System.out.println("Find all orders: ");
        order.findAll().forEach(System.out::println);
    }
}