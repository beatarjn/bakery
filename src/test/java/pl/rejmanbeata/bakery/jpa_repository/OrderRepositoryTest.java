package pl.rejmanbeata.bakery.jpa_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.database.ProductEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.rejmanbeata.bakery.jpa_repository.TestHelper.createOrderEntity;

@ActiveProfiles("test")
@DataJpaTest
class OrderRepositoryTest {

    public static final double PRICE = 2.50;
    public static final String DONUT_OREO = "Donut Oreo";
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
    void shouldSaveOrder() {
        ProductEntity product = new ProductEntity(DONUT_OREO, PRICE);

        productRepository.save(product);

        OrderEntity order = createOrderEntity(product, 10);

        OrderEntity savedOrder = orderRepository.save(order);

        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getProduct()).isSameAs(product);
    }

    @Test
    void shouldFindOrderById() {
        ProductEntity product = new ProductEntity(DONUT_OREO, PRICE);
        productRepository.save(product);

        OrderEntity order = createOrderEntity(product, 5);
        OrderEntity savedOrder = orderRepository.save(order);

        Optional<OrderEntity> foundOrder = orderRepository.findById(savedOrder.getId());

        assertThat(foundOrder)
                .isPresent()
                .containsSame(savedOrder);
    }


}