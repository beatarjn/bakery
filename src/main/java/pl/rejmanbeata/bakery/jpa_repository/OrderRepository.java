package pl.rejmanbeata.bakery.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rejmanbeata.bakery.database.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    boolean findByProductId(long productId);
}
