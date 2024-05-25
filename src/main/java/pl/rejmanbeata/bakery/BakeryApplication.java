package pl.rejmanbeata.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.rejmanbeata.bakery.database.AddressEntity;

@ComponentScan(basePackages = {"pl.rejmanbeata.bakery", "com.fasterxml...."})
@SpringBootApplication
public class BakeryApplication {

    public static void main(String[] args) {

        // 1 - new
        AddressEntity addressEntity = new AddressEntity();

        // 2 - builder
        AddressEntity addressEntity1 = AddressEntity.builder().build();

        // 3 - Fabryka vs statyczne metody fabryczne
        AddressEntity addressEntity2 = AddressEntity.createAddress();
        //  AddressEntity addressEntity2 = new AddressFactory().createAddress();

        // 4 - Spring. Inversion of Control - odwrócenie kontroli (zależności)
        // Dependency Injection - wstrzykiwanie zależności
        ConfigurableApplicationContext context = SpringApplication.run(
            BakeryApplication.class,
            args
        );
        var addressService = context.getBean(AddressService.class);

        System.out.println(addressService);


        /// ----  zagrożeniem przy wielowątkowości, obiekt można odczytać w niepełnym stanie
        // jeszcze bez ustawionego pola
//        AddressService addressService = new AddressService();
//        addressService.setAddressRepository(addressRepository);
    }
}