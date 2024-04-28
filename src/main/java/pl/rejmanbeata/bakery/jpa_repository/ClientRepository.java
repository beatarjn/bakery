package pl.rejmanbeata.bakery.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.rejmanbeata.bakery.database.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
