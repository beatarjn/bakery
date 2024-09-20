package pl.rejmanbeata.bakery.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.rejmanbeata.bakery.database.OrderEntity;
import pl.rejmanbeata.bakery.jpa_repository.OrderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderEntity save(OrderEntity address) {
        return orderRepository.save(address);
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public boolean findByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }
}
