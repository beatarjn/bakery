package pl.rejmanbeata.bakery.service.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.jpa_repository.OrderRepository;
import pl.rejmanbeata.bakery.service.OrderService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createOrderEntity;
import static pl.rejmanbeata.bakery.TestEntitiesFactory.createProductEntity;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class OrderServiceITest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testFindOrderById_shouldReturnOrder() {
        OrderEntity orderEntity = orderService.getOrderById(1L);

        assertNotNull(orderEntity);
    }

    @Test
    void testFindOrderById_shouldReturnNull() {
        OrderEntity orderEntity = orderService.getOrderById(8L);

        assertNull(orderEntity);
    }

    @Test
    void testGetAllOrders_shouldFindAll() {
        List<OrderEntity> allOrders = orderService.getAllOrders();

        assertNotNull(allOrders);
        assertEquals(3, allOrders.size());
    }

    @Test
    void testSave_shouldSaveOrder() {
        OrderEntity orderEntity = createOrderEntity(createProductEntity("Donut", 2.5), 5);

        List<OrderEntity> allOrders = orderService.getAllOrders();
        assertNotNull(allOrders);

        orderRepository.save(orderEntity);

        List<OrderEntity> allOrdersAfterSave = orderService.getAllOrders();

        assertThat(allOrdersAfterSave).hasSize(allOrders.size() + 1);
    }

    @Test
    void testDeleteById_shouldDeleteOrder() {
        OrderEntity orderEntity = createOrderEntity(createProductEntity("Donut", 2.5), 5);
        orderService.save(orderEntity);
        List<OrderEntity> allOrders = orderService.getAllOrders();
        assertNotNull(allOrders);

        orderService.deleteOrderById(orderEntity.getId());

        List<OrderEntity> allOrdersAfterDelete = orderService.getAllOrders();

        assertThat(allOrdersAfterDelete).hasSize(allOrders.size() - 1);
        assertNull(orderService.getOrderById(orderEntity.getId()));
    }
}
