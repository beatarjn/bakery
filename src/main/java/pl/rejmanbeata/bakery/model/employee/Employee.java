package pl.rejmanbeata.bakery.model.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private String name;
    private String lastName;

    //TODO role to enum?
    private String role;
}
