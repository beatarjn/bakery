package pl.rejmanbeata.bakery.database;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;


//    Here, an Order can have a many-to-one relationship with Product, meaning many orders
//    can be associated with one product. The @JoinColumn annotation specifies the foreign
//    key column (product_id) in the orders table that maps to the primary key (id) in the
//    products table.

}