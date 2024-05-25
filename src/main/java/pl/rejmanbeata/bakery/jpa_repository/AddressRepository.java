package pl.rejmanbeata.bakery.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.rejmanbeata.bakery.database.AddressEntity;

//@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
}
