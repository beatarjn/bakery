package pl.rejmanbeata.bakery.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClientEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

//    Client can have a many-to-one relationship with Address, meaning many clients can have the same address.
//    The @JoinColumn annotation specifies the foreign key column (address_id) in the clients table that maps
//    to the primary key (id) in the address table.

//     Client can have a one-to-one relationship with Order, meaning each client can have one order
//     associated with them. The @JoinColumn annotation specifies the foreign key column (order_id)
//     in the clients table that maps to the primary key (id) in the orders table.

}
