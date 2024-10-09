package pl.rejmanbeata.buns.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
public class Bun {
    private String id;
    private String name;
    private LocalDate productionDate;
    private LocalDate expirationDate;
    private List<Allergen> allergens;
    private double productionPrice;

}