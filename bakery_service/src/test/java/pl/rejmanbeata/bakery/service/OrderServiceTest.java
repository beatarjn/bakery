package pl.rejmanbeata.bakery.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.database.ProductEntity;
import pl.rejmanbeata.bakery.jpa_repository.OrderRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;
    private OrderEntity order;

    @BeforeEach
    public void setup() {
        order = OrderEntity.builder()
                .id(1L)
                .product(new ProductEntity(1L, "product", 5.50))
                .quantity(2)
                .build();
    }

    @Test
    void testSave_shouldSaveAndReturnOrder() {
        given(orderRepository.save(order)).willReturn(order);

        OrderEntity savedOrder = orderService.save(order);

        assertThat(savedOrder).isNotNull();
    }

    @Test
    void testGetAllOrders_shouldReturnAllOrders() {
        OrderEntity order2 = OrderEntity.builder()
                .id(1L)
                .product(new ProductEntity(2L, "product2", 3.50))
                .quantity(4)
                .build();

        given(orderRepository.findAll()).willReturn(List.of(order, order2));

        List<OrderEntity> orders = orderService.getAllOrders();

        assertThat(orders)
                .isNotNull()
                .hasSize(2);
    }

    @Test
    void testGetAllOrders_shouldReturnEmptyList() {
        given(orderRepository.findAll()).willReturn(Collections.emptyList());

        List<OrderEntity> orders = orderService.getAllOrders();

        assertThat(orders).isEmpty();
    }

    @Test
    void testGetOrderById_shouldReturnOrderById() {
        given(orderRepository.findById(1L)).willReturn(Optional.of(order));

        OrderEntity savedOrder = orderService.getOrderById(order.getId());

        assertThat(savedOrder).isNotNull();
    }

    @Test
    void testDelete_shouldDeleteOrderById() {
        long orderId = 1L;

        willDoNothing().given(orderRepository).deleteById(orderId);

        orderService.deleteOrderById(orderId);

        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void testFindByProductId_shouldFindProduct() {
        given(orderRepository.findByProductId(1L)).willReturn(true);

        boolean order = orderService.findByProductId(1L);

        assertTrue(order);
    }
}