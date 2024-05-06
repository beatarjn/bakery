package pl.rejmanbeata.bakery.jpa_repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ActiveProfiles;
import pl.rejmanbeata.bakery.database.OrderEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

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
}