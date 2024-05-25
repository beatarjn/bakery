package pl.rejmanbeata.bakery;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.rejmanbeata.bakery.jpa_repository.AddressRepository;

@Configuration
public class AddressServiceConfiguration {

  @Bean(name = "readService")
  public AddressService addressReadService(AddressRepository addressRepository){
    return new AddressService(addressRepository);
  }

  @Bean(name = "writeService")
  public AddressService addressWriteService(AddressRepository addressRepository){
    return new AddressService(addressRepository);
  }
}
