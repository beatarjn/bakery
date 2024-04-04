package pl.rejmanbeata.bakery.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "address")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AddressEntity {

    @Id
    @Column
    private long id;
    @Column
    private double longitude;
    @Column
    private double latitude;

}
