package pl.rejmanbeata.bakery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BakeryApplication {


    public static void main(String[] args) {
		SpringApplication.run(BakeryApplication.class, args);

//        AddressRepository addressRepository = new CsvBasedAddressRepository();
//
//        ClientRepository clientRepository = new CsvBasedClientRepository(addressRepository);
//
//        System.out.println("Client found: ");
//        System.out.println(clientRepository.findByName("Aam"));

//        clientRepository.saveNewClient("Adam", "Test", new Address(5, 65));
//        clientRepository.saveNewClient("Hanna", "Blue", new Address(1, 34));
//        clientRepository.saveNewClient("Jerry", "Morgan", new Address(23, 64));
//
//        List<Client> clients = clientRepository.findAll();
//
//        System.out.println("All clients: ");
//        clients.forEach(System.out::println);
//
//        System.out.println("Client found: ");
//        System.out.println(clientRepository.findByName("Jerry"));
//
//        System.out.println("Client not found: ");
//        System.out.println(clientRepository.findByName("Anna"));
//
//        System.out.println("Remove client: Adam ");
//        clientRepository.deleteClient("Adam");
//
//        System.out.println("All clients after removing Adam: ");
//        clients.forEach(System.out::println);
//
//        clientRepository.updateClient("Hanna", "Maria", "Novak",
//                new Address(65, 7));
//
//        System.out.println("All clients after updating Hanna: ");
//        clients.forEach(System.out::println);
    }
}