package pl.rejmanbeata.bakery.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Column(name = "role")
    private String role;

}
