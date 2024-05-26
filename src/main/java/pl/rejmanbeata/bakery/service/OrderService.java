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

    public void save(OrderEntity address) {
        orderRepository.save(address);
    }

    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    public OrderEntity findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }

    public boolean findByProductId(Long productId) {
        return orderRepository.findByProductId(productId);
    }
}
