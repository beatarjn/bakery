package pl.rejmanbeata.bakery;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import pl.rejmanbeata.bakery.jpa_repository.AddressRepository;

@AllArgsConstructor // 1
@Component
@Controller
@RestController
@Repository
@Service
public final class AddressService {
  // 3
  @Autowired
  private final AddressRepository addressRepository;

  // Guice - DI, IoC

  // 2 setter
//  public void setAddressRepository(AddressRepository addressRepository) {
//    this.addressRepository = addressRepository;
//  }
}
