package pl.rejmanbeata.bakery.jpa_repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.rejmanbeata.bakery.database.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query
    ClientEntity findByLastName(String lastName);
}
