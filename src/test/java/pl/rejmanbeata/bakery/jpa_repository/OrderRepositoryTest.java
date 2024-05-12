package pl.rejmanbeata.bakery.jpa_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.database.ProductEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldReturnListWhenFindAll() {
        List<OrderEntity> allOrders = orderRepository.findAll();

        assertNotNull(allOrders);
        assertEquals(3, allOrders.size());
    }

    @Test
    void shouldReturnEmptyOptionalWhenFindById_IdNotExisting() {
        Optional<OrderEntity> order = orderRepository.findById(5L);

        assertThat(order).isEmpty();
    }

    @Test
    void shouldReturnOptionalWhenFindById_IdExisting() {
        Optional<OrderEntity> order = orderRepository.findById(1L);

        assertTrue(order.isPresent());
    }

    @Test
    void shouldThrowExceptionWhenFindById_IdNull() {
        assertThrows(InvalidDataAccessApiUsageException.class, () -> orderRepository.findById(null));
    }

    @Test
    void testSaveOrder() {
        ProductEntity product = new ProductEntity("Donut Oreo", 2.50);

        productRepository.save(product);

        OrderEntity order = createOrderEntity(product, 10);

        OrderEntity savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getProduct()).isSameAs(product);
    }

    @Test
    void testFindOrderById() {
        ProductEntity product = new ProductEntity("Donut Oreo", 2.50);
        productRepository.save(product);

        OrderEntity order = createOrderEntity(product, 5);
        OrderEntity savedOrder = orderRepository.save(order);

        Optional<OrderEntity> foundOrder = orderRepository.findById(savedOrder.getId());

        assertThat(foundOrder)
                .isPresent()
                .containsSame(savedOrder);
    }

    private static OrderEntity createOrderEntity(ProductEntity product, Integer quantity) {
        OrderEntity order = new OrderEntity();
        order.setProduct(product);
        order.setQuantity(quantity);
        return order;
    }

}